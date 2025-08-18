package com.local.ecom.data.services.user;

import com.local.ecom.data.entities.user.CustomerUser;
import com.local.ecom.data.repository.user.CustomerUserRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserService extends BaseService<CustomerUser, Long> implements UserDetailsService {

    private final CustomerUserRepository customerUserRepository;

    @Autowired
    public CustomerUserService(CustomerUserRepository customerUserRepository) {
        super(customerUserRepository, customerUserRepository);
        this.customerUserRepository = customerUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerUserRepository.findByUsername(username).map(customerUser ->
            User.withUsername(customerUser.getUsername()).password(customerUser.getPassword()).build()
        ).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}