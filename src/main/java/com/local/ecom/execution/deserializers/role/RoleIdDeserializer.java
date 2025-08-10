package com.local.ecom.execution.deserializers.role;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.local.ecom.data.entities.role.Role;
import com.local.ecom.data.repository.role.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RoleIdDeserializer extends JsonDeserializer<Role> {

    private RoleRepository roleRepository;

    @Override
    public Role deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        Long roleId = jsonParser.getLongValue();
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}