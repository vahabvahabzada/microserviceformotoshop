package com.microservices.microservice1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.mappers.CarMapper;
import com.microservices.microservice1.repos.CarRepo;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CarMapper carMapper;

    public List<String> listBrands(){
        return carRepo.listBrands();
    }

    public List<String> listModels(String brandName){
        return carRepo.listModels(brandName);
    }

    public List<CarDto> getCars(CarDto targetCar,Integer priceMin,Integer priceMax,Integer yearMin,Integer yearMax){
        Car target=new Car();
        target=carMapper.dtoToEntity(targetCar);
        System.out.println("Target Car : "+target);
        //List<Car> results=carRepo.findByBrandAndModelAndColor(target.getBrand(), target.getModel(), target.getColor());
        List<Car> results=carRepo.getCars(target,priceMin,priceMax,yearMin,yearMax);
        List<CarDto> dtoResults=new ArrayList<>();
        for(Car result:results){
        dtoResults.add(carMapper.entityToDto(result));
        }

        return dtoResults;
    }
}
