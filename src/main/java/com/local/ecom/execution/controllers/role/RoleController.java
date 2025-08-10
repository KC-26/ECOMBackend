package com.local.ecom.execution.controllers.role;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.role.Role;
import com.local.ecom.data.services.role.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> getRoles(@RequestParam(required = false) String filter) {
        return roleService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addRoles(@RequestBody JsonNode jsonNode) {
        return roleService.addAll(jsonNode, Role.class);
    }
}