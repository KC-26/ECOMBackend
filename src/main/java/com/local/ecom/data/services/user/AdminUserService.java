package com.local.ecom.data.services.user;

import com.local.ecom.data.entities.user.AdminUser;
import com.local.ecom.data.repository.user.AdminUserRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService extends BaseService<AdminUser, Long> {

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository) {
        super(adminUserRepository, adminUserRepository);
    }
}
