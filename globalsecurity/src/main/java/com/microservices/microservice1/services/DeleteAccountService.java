package com.microservices.microservice1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.jwt.JWTGenerator;
//import com.microservices.microservice1.repos.BlackListRepo;
import com.microservices.microservice1.repos.UsersRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeleteAccountService {
    @Autowired
    private UsersRepo usersRepo;
    
    //@Autowired
    //private BlackListRepo blackListRepo;
    
    @Autowired
    private JWTGenerator jwtGenerator;

    public void deleteAccount(String token){
        String username=jwtGenerator.getUsernameFromToken(token);
        usersRepo.deleteByUsername(username);
        SecurityContextHolder.clearContext();
    }
}
