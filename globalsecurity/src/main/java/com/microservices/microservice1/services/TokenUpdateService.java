package com.microservices.microservice1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.jwt.JWTGenerator;

@Service
public class TokenUpdateService {
    @Autowired
    private JWTGenerator jwtGenerator;

    public String getNewToken(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String newToken=jwtGenerator.genetateToken(authentication);
        return newToken;
    }
}