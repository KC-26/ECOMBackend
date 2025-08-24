package com.local.ecom.data.repository.user;

import com.local.ecom.data.entities.user.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long>, JpaSpecificationExecutor<CustomerUser> {
    Optional<CustomerUser> findByUsername(String username);
}