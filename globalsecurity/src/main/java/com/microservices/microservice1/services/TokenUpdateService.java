package com.microservices.microservice1.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.jwt.JWTGenerator;

@Service
public class TokenUpdateService {
    private final JWTGenerator jwtGenerator;

    public TokenUpdateService(JWTGenerator jwtGenerator){
        this.jwtGenerator=jwtGenerator;
    }

    public String getNewToken(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String newToken=jwtGenerator.genetateToken(authentication);
        return newToken;
    }
}
