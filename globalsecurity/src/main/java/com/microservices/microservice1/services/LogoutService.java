package com.microservices.microservice1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.entities.BlackList;
import com.microservices.microservice1.jwt.JWTGenerator;
import com.microservices.microservice1.repos.BlackListRepo;
import com.microservices.microservice1.repos.UsersRepo;

@Service
public class LogoutService {
    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private BlackListRepo repo;

    @Autowired
    private UsersRepo usersRepo;

    public void logout(String token) {
        System.out.println("Inside logout method");
        BlackList blocked = new BlackList();
        blocked.setToken(token);
        blocked.setExp_time(jwtGenerator.getExpireDate(token));
        blocked.setUsername(jwtGenerator.getUsernameFromToken(token));
        blocked.setTargetUser(usersRepo.findByUsername(jwtGenerator.getUsernameFromToken(token)));
        repo.save(blocked);

        System.out.println("Saved to Blacklist!");
        SecurityContextHolder.clearContext();
    }
}
