package com.microservices.microservice1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.services.BlackListCommunicationService;

@RestController
public class BlackListCommunicationController {
    @Autowired
    private BlackListCommunicationService service;

    @PostMapping("/blacklist")
    public String findByToken(@RequestBody String token){
        return service.findByToken(token);
    }
}
