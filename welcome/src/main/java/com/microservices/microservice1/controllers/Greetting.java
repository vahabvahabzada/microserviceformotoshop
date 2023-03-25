package com.microservices.microservice1.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.configurations.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Greetting {
    @Autowired
    CustomUserDetails service;
    @GetMapping("/welcome")
    public String greet() {
        System.out.println("welocome works");
        service.loadUserByUsername("user1");//serti olaraq yazmisam
        return "Welcome to VLA :)";
    }
}
