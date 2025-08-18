package com.local.ecom.execution.limiters;

import com.local.ecom.execution.limiters.authentication.JwtUtil;
import com.local.ecom.execution.limiters.ratelimiting.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final RateLimiterService rateLimiterService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, @Lazy UserDetailsService userDetailsService, RateLimiterService rateLimiterService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String fullToken = request.getHeader("Authorization");

        if(fullToken != null && fullToken.startsWith("Bearer ")) {
            String token = fullToken.substring(7);
            String username = jwtUtil.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(jwtUtil.isValidToken(token,userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        String ip = request.getRemoteAddr();

        if(rateLimiterService.tryConsume(ip)) {
            filterChain.doFilter(request,response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write(String.format("Too many requests from IP: %s.",ip));
        }
    }
}
