package com.microservices.microservice1.communication;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.repos.CarRepo;
import com.microservices.microservice1.repos.PhotoRepo;
import com.microservices.microservice1.repos.UserRepo;

@Service
public class AccountDelService {
    private final UserRepo userRepo;

    private final PhotoRepo photoRepo;

    private final CarRepo carRepo;

    public AccountDelService(UserRepo userRepo,PhotoRepo photoRepo,CarRepo carRepo){
        this.userRepo=userRepo;
        this.photoRepo=photoRepo;
        this.carRepo=carRepo;
    }

    public int deleteAccount(String username){
        Long targetId=userRepo.findByUsername(username).getUserId();
        List<Car> targetCars=carRepo.getCarsByUserId(targetId);

        for(Car masin:targetCars){
            photoRepo.deleteByCarId(masin.getCarId());// delete target photos
        }
        System.out.println("Removed successfully from photos...");

        carRepo.deleteByUserId(targetId);// delete target cars
        System.out.println("Removed successfully from cars...");
        
        return userRepo.deleteByUsername(username);// delete targetUser
    }
}
