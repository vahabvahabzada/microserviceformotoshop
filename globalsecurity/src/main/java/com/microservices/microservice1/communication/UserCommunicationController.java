package com.microservices.microservice1.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.dtos.UserDto;

@RestController
public class UserCommunicationController {
    @Autowired
    private UserCommunicationService service;

    @PostMapping("/userdetails")
    public UserDto findByUsername(@RequestBody String name){
        return service.findByUserName(name);
    }
}
