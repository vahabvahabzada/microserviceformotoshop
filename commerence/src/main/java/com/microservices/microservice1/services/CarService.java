package com.microservices.microservice1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.repos.CarRepo;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;

    public List<String> listBrands(){
        return carRepo.listBrands();
    }

    public List<String> listModels(String brandName){
        return carRepo.listModels(brandName);
    }

    public List<CarDto> getCars(CarDto targetCar/*,int minPrice,int maxPrice*/){
        Car target=new Car();
        target.setBanStyle(targetCar.getBanStyle());
        target.setBarter(targetCar.getBarter());
        target.setBrand(targetCar.getBrand());
        target.setColor(targetCar.getColor());
        target.setCredit(targetCar.getCredit());
        target.setCurrency(targetCar.getCurrency());
        target.setKilometers(targetCar.getKilometers());
        target.setModel(targetCar.getModel());
        target.setPrice(targetCar.getPrice());
        target.setYear(targetCar.getYear());
        
        //List<Car> results=carRepo.findByBrandAndModelAndColor(target.getBrand(), target.getModel(), target.getColor());

        List<Car> results=carRepo.getCars(target);
        List<CarDto> dtoResults=new ArrayList<>();
        for(Car result:results){
        targetCar.setBanStyle(result.getBanStyle());
        targetCar.setBarter(result.getBarter());
        targetCar.setBrand(result.getBrand());
        targetCar.setColor(result.getColor());
        targetCar.setCredit(result.getCredit());
        targetCar.setCurrency(result.getCurrency());
        targetCar.setKilometers(result.getKilometers());
        targetCar.setModel(result.getModel());
        targetCar.setPrice(result.getPrice());
        targetCar.setYear(result.getYear());

        dtoResults.add(targetCar);
        }

        return dtoResults;
    }
}
