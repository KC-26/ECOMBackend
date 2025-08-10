package com.local.ecom.execution.controllers.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.user.CustomerUser;
import com.local.ecom.data.services.user.CustomerUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/user")
public class CustomerUserController {

    private final CustomerUserService customerUserService;

    @GetMapping
    public List<CustomerUser> getCustomerUsers(@RequestParam(required = false) String filter) {
        return customerUserService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addCustomerUsers(@RequestBody JsonNode jsonNode) {
        return customerUserService.addAll(jsonNode, CustomerUser.class);
    }
}
