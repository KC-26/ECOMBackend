package com.local.ecom.data.services.user;

import com.local.ecom.data.entities.user.CustomerUser;
import com.local.ecom.data.repository.user.CustomerUserRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserService extends BaseService<CustomerUser, Long> {

    @Autowired
    public CustomerUserService(CustomerUserRepository customerUserRepository) {
        super(customerUserRepository, customerUserRepository);
    }
}