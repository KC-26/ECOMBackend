package com.local.ecom.execution.controllers.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.user.AdminUser;
import com.local.ecom.data.services.user.AdminUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/admin_user")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public List<AdminUser> getAdminUsers(@RequestParam(required = false) String filter) {
        return adminUserService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addAdminUsers(@RequestBody JsonNode jsonNode) {
        return adminUserService.addAll(jsonNode, AdminUser.class);
    }

    @PutMapping
    public ResponseEntity<Object> updateAdminUsers(@RequestBody JsonNode jsonNode) {
        return adminUserService.updateAll(jsonNode, AdminUser.class);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAdminUsers(@RequestBody JsonNode jsonNode) {
        return adminUserService.deleteAll(jsonNode, AdminUser.class);
    }
}