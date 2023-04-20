package com.microservices.microservice1.services;

import java.util.Set;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.entities.Role;
import com.microservices.microservice1.jwt.JWTGenerator;
import com.microservices.microservice1.repos.UsersRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeleteAccountService {
    //@Autowired
    private final UsersRepo usersRepo;

    //@Autowired
    private final JWTGenerator jwtGenerator;

    public DeleteAccountService(UsersRepo usersRepo,JWTGenerator jwtGenerator){
        this.usersRepo=usersRepo;
        this.jwtGenerator=jwtGenerator;
    }

    public void deleteAccount(String token) {
        String username = jwtGenerator.getUsernameFromToken(token);
        Long userId = usersRepo.findByUsername(username).getUserId();

        Set<Role> rollar = usersRepo.findByUsername(username).getRoles();
        for (Role rol : rollar) {
            usersRepo.deleteRelation(userId, rol.getId());
        }

        // usersRepo.deleteRelation(null, null);
        usersRepo.deleteByUsername(username);
        SecurityContextHolder.clearContext();
    }
}
