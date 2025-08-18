package com.local.ecom.data.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.ecom.execution.filters.SpecificationFactory;
import com.local.ecom.data.entities.user.AdminUser;
import com.local.ecom.data.entities.user.CustomerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static com.local.ecom.util.EComLogger.logError;

public abstract class BaseService<T, U> {

    private static final String ERROR_KEY = "error";

    private final JpaRepository<T, U> jpaRepository;
    private final JpaSpecificationExecutor<T> specRepository;
    private ObjectMapper mapper;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    protected BaseService(JpaRepository<T, U> jpaRepository,
                          JpaSpecificationExecutor<T> specRepository) {
        this.jpaRepository = jpaRepository;
        this.specRepository = specRepository;
    }

    public List<T> getAll(String filter) {
        if (filter != null && !filter.isBlank()) {
            Specification<T> specification = SpecificationFactory.build(filter);
            if (specification != null) {
                return specRepository.findAll(specification);
            }
        }
        return jpaRepository.findAll();
    }

    public ResponseEntity<Object> addAll(JsonNode jsonNode, Class<T> type) {
        List<T> validEntries = new ArrayList<>();
        List<Map<String, String>> errorEntries = new ArrayList<>();

        List<JsonNode> nodes = extractJsonNodes(jsonNode);
        if (nodes.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(ERROR_KEY, "Invalid request JSON."));
        }

        convertEntities(type, nodes, validEntries, errorEntries);

        List<T> savedEntities = new ArrayList<>();

        for (T entity : validEntries) {
            if ((entity instanceof AdminUser || entity instanceof CustomerUser)
                    && !encryptUserPassword(entity,errorEntries)) {
                continue;
            }
            try {
                T savedEntity = jpaRepository.save(entity);
                savedEntities.add(savedEntity);
            } catch (Exception exception) {
                logError(String.format("Exception occurred while insert entity with the body: %s",entity),exception);
                errorEntries.add(Map.of(ERROR_KEY, String.format("Invalid entity JSON: %s",entity)));
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", savedEntities);
        if (!errorEntries.isEmpty()) {
            response.put("failure", errorEntries);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private boolean encryptUserPassword(Object user, List<Map<String, String>> errorEntries) {
        String password = null;
        String username = null;

        if (user instanceof AdminUser adminUser) {
            password = adminUser.getPassword();
            username = adminUser.getUsername();
        } else if (user instanceof CustomerUser customerUser) {
            password = customerUser.getPassword();
            username = customerUser.getUsername();
        }

        if (passwordEncoder == null) {
            errorEntries.add(Map.of(ERROR_KEY, "Unable to process request at this time, please try again later."));
            return false;
        }

        if (password == null || password.isBlank()) {
            errorEntries.add(Map.of(ERROR_KEY,
                    String.format("Password not provided for user: %s", username)));
            return false;
        }

        String hashedPassword = passwordEncoder.encode(password);

        if (user instanceof AdminUser adminUser) {
            adminUser.setPassword(hashedPassword);
        } else {
            CustomerUser customerUser = (CustomerUser) user;
            customerUser.setPassword(hashedPassword);
        }

        return true;
    }

    private void convertEntities(Class<T> type, List<JsonNode> nodes, List<T> validEntries, List<Map<String, String>> errorEntries) {
        for (JsonNode node : nodes) {
            try {
                T entity = mapper.convertValue(node, type);
                validEntries.add(entity);
            } catch (IllegalArgumentException e) {
                errorEntries.add(Map.of(ERROR_KEY, String.format("Invalid entity JSON: %s",node.toString())));
            }
        }
    }

    private List<JsonNode> extractJsonNodes(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            List<JsonNode> nodes = new ArrayList<>();
            jsonNode.forEach(nodes::add);
            return nodes;
        } else if (jsonNode.isObject()) {
            return Collections.singletonList(jsonNode);
        }
        return Collections.emptyList();
    }

//    private PasswordEncoder getPasswordEncoder() {
//        try {
//            return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, BCRYPT_STRENGTH,
//                    SecureRandom.getInstanceStrong());
//        } catch (NoSuchAlgorithmException e) {
//            return null;
//          }
//    }
}