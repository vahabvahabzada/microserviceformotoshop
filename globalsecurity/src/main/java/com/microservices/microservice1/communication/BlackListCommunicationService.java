package com.microservices.microservice1.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.repos.BlackListRepo;

@Service
public class BlackListCommunicationService {
    @Autowired
    private BlackListRepo repo;
    public String findByToken(String token){
        if(repo.findByToken(token)!=null){
            return repo.findByToken(token).getToken();
        }
        return null;
    }
}
