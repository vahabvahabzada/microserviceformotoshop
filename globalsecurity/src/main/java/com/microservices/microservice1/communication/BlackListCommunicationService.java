package com.microservices.microservice1.communication;

import org.springframework.stereotype.Service;

import com.microservices.microservice1.repos.BlackListRepo;

@Service
public class BlackListCommunicationService {
    private final BlackListRepo repo;
    public BlackListCommunicationService(BlackListRepo blackListRepo){
        this.repo=blackListRepo;
    }

    public String findByToken(String token){
        if(repo.findByToken(token)!=null){
            return repo.findByToken(token).getToken();
        }
        return null;
    }
}
