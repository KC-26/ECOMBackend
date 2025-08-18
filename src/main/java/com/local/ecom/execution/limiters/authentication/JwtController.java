package com.local.ecom.execution.limiters.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class JwtController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public JwtResponse getToken(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        User user = (User) authentication.getPrincipal();
        return getJwtResponse(user);
    }

    private JwtResponse getJwtResponse(User user) {
        String token = jwtUtil.generateToken(user.getUsername());
        Date expiryDate = jwtUtil.extractExpiration(token);
        ZonedDateTime expiresAt = expiryDate.toInstant().atZone(ZoneId.systemDefault());
        return new JwtResponse(token, expiresAt);
    }
}
