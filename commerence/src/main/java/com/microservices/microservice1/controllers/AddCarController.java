package com.microservices.microservice1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.services.AddCarService;

@RestController
public class AddCarController {
    @Autowired
    private AddCarService addCarservice;

    @PostMapping(value = "/addnewcar", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public Boolean addNewCar( /* @RequestBody */@RequestPart("car") CarDto newCar,@RequestPart("file") List<MultipartFile> photos) {
        System.out.println("AddController.java -->Active");
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")) || SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("SELLER"))){
            return addCarservice.addNewCar(newCar, photos);
        }
        return false;
    }
}