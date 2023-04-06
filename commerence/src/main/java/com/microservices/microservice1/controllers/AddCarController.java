package com.microservices.microservice1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.services.AddCarService;

@RestController
public class AddCarController {
    @Autowired
    private AddCarService addCarservice;

    @PostMapping("/addnewcar")
    public Boolean addNewCar( @RequestBody CarDto newCar){
        return addCarservice.addNewCar(newCar);
    }
}
