package com.local.ecom.data.services.role;

import com.local.ecom.data.entities.role.Role;
import com.local.ecom.data.repository.role.RoleRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, Long> {

    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository, repository);
    }
}