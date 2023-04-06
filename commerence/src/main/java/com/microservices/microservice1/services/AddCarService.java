package com.microservices.microservice1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.mappers.CarMapper;
import com.microservices.microservice1.repos.CarRepo;
import com.microservices.microservice1.repos.UserRepo;

@Service
public class AddCarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private UserRepo userRepo;

    public Boolean addNewCar(CarDto newCar){
        Car car=new Car();
        car=carMapper.dtoToEntity(newCar);
        car.setHost(userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        carRepo.save(car);
        return true; // yuxaridaki emeliyyatlarda problem olsa kod hec bura cata bilmeyecek,demeli emeliyyat ugurludusa true-du
    }
}
