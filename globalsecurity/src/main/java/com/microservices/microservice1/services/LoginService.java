package com.microservices.microservice1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.UserDto;
//import com.microservices.microservice1.entities.User;
//import com.microservices.microservice1.repos.UsersRepo;

@Service
public class LoginService {
    //@Autowired
    //UsersRepo repo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;
    public String login(UserDto user){
        /*User targetUser=new User();
        targetUser.setUsername(user.getUsername());
        targetUser.setPassword(user.getPassword());*/

        /*if(repo.findByUsername(targetUser.getUsername())==null){
            return null;
        }
        if(!repo.findByUsername(targetUser.getUsername()).getPassword().equals(targetUser.getPassword())){
            return null;
        }*/

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtGenerator.genetateToken(authentication);
        return token;
    }
}
