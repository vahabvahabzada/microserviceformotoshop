package com.microservices.microservice1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.services.LoginService;

@RestController
public class LoginController {
    @Autowired
    LoginService service;

    @PostMapping("/login")
    public String login(@RequestBody UserDto user){
        return service.login(user);
    }
}
