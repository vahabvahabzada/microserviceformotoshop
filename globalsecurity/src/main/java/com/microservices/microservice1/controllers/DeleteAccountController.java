package com.microservices.microservice1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.services.DeleteAccountService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DeleteAccountController {
    @Autowired
    private DeleteAccountService service;
    @GetMapping("/deleteaccounturl")
    public void deleteAccount(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        service.deleteAccount(token);
    }
}
