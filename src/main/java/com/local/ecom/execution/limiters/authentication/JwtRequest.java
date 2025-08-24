package com.local.ecom.execution.limiters.authentication;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}