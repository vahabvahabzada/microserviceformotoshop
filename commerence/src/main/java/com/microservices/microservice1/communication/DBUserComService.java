package com.microservices.microservice1.communication;

import org.springframework.stereotype.Service;

import com.microservices.microservice1.entities.UserEntity;
import com.microservices.microservice1.repos.UserRepo;

@Service
public class DBUserComService {
    private final UserRepo userRepo;
    public DBUserComService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    public Boolean saveUserToDB(String newUser){
        UserEntity user=new UserEntity();
        //user.setName(newUser);
        user.setUsername(newUser);
        userRepo.save(user);
        return true;
    }
}
