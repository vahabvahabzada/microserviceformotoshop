package com.microservices.microservice1.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.entities.BlackList;
import com.microservices.microservice1.jwt.JWTGenerator;
import com.microservices.microservice1.repos.BlackListRepo;
import com.microservices.microservice1.repos.UsersRepo;

@Service
public class LogoutService {
    private final JWTGenerator jwtGenerator;

    private final BlackListRepo repo;

    private final UsersRepo usersRepo;

    public LogoutService(JWTGenerator jwtGenerator,BlackListRepo repo,UsersRepo usersRepo){
        this.jwtGenerator=jwtGenerator;
        this.repo=repo;
        this.usersRepo=usersRepo;
    }

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
