package com.microservices.microservice1.services;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.UserDto;
//import com.microservices.microservice1.entities.User;
//import com.microservices.microservice1.repos.UsersRepo;
import com.microservices.microservice1.jwt.JWTGenerator;

@Service
public class LoginService {
    //@Autowired
    private final AuthenticationManager authenticationManager;

    //@Autowired
    private final JWTGenerator jwtGenerator;
    
    public LoginService(AuthenticationManager authenticationManager,JWTGenerator jwtGenerator){
        this.authenticationManager=authenticationManager;
        this.jwtGenerator=jwtGenerator;
    }

    public String login(UserDto user){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtGenerator.genetateToken(authentication);
        return token;
    }
}
