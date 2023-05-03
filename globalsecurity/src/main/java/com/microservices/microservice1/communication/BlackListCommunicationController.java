package com.microservices.microservice1.communication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackListCommunicationController {
    
    private BlackListCommunicationService service;
    public BlackListCommunicationController(BlackListCommunicationService blackListCommunicationService){
        this.service=blackListCommunicationService;
    }

    @PostMapping("/blacklist")
    public String findByToken(@RequestBody String token){
        return service.findByToken(token);
    }
}
