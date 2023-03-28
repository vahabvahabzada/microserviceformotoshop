package com.microservices.microservice1.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtIcaze {
    @PostMapping("/myname")
    public String myNameIs(@RequestBody String name){
        return "Hi,I am "+name;
    }
}
