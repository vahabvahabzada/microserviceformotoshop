package com.microservices.microservice1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.microservice1.jwt.JWTGenerator;
import com.microservices.microservice1.services.DeleteAccountService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DeleteAccountController {
    private DeleteAccountService service;
    private final RestTemplate restTemplate;
    private JWTGenerator jwtGenerator;
    public DeleteAccountController(DeleteAccountService service,RestTemplate restTemplate,JWTGenerator jwtGenerator){
        this.service=service;
        this.restTemplate=restTemplate;
        this.jwtGenerator=jwtGenerator;
    }

    @GetMapping("/deleteaccounturl")
    public void deleteAccount(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        service.deleteAccount(token);
        restTemplate.postForObject("http://localhost:8081/deleteaccounturl",jwtGenerator.getUsernameFromToken(token), Boolean.class);
        System.out.println("#### Deleting from 8081 ####");
    }
}
