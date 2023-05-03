package com.microservices.microservice1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.services.TokenUpdateService;

@RestController
public class TokenUpdateContreller {
    private TokenUpdateService service;
    public TokenUpdateContreller(TokenUpdateService service){
        this.service=service;
    }

    @GetMapping("/updatetoken")//her token vaxti bitmeye az qalmis (meselen 1 deqiqe qalmis,bura request atib update etmek)
    public String getNewToken(){
        return service.getNewToken();
    }    
}
