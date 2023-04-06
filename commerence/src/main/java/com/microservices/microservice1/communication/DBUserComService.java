package com.microservices.microservice1.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.entities.UserEntity;
import com.microservices.microservice1.repos.UserRepo;

@Service
public class DBUserComService {
    @Autowired
    private UserRepo userRepo;
    public Boolean saveUserToDB(String newUser){
        UserEntity user=new UserEntity();
        //user.setName(newUser);
        user.setUsername(newUser);
        userRepo.save(user);
        return true;
    }
}
