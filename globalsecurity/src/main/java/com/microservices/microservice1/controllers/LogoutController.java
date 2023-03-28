package com.microservices.microservice1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.services.LogoutService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {
    @Autowired
    private LogoutService service;

    @GetMapping("/logouturl") //default olaraq /logout url -i movcuddur deye, /logouturl yaziriq
    public void logout(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);// "Bearer "
        service.logout(token);
        //return service.logout(token);
    }
}
