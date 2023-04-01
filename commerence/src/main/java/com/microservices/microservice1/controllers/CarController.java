package com.microservices.microservice1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.dtos.GetCars;
import com.microservices.microservice1.services.CarService;


@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/listbrands")
    public List<String> listBrands(){
        return carService.listBrands();
    }//lists all options when cliked on brands option in UI

    @GetMapping("/listmodels")
    public List<String> listModels(@RequestBody String brandName){
        return carService.listModels(brandName);
    }

    @GetMapping("/getcars")
    public List<CarDto> getCars(@RequestBody GetCars data){//ger bir secime click edednde,uygun olaraq filterleyib neticeleri cixarir ve elecede qiymet ve il araligini teyin edende
        return carService.getCars(data.getCarDto(),data.getPriceMin(),data.getPriceMax(),data.getYearMin(),data.getYearMax());
    }
}
