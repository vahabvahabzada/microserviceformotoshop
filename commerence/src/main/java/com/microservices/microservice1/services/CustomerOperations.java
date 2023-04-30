package com.microservices.microservice1.services;

import java.util.ArrayList;
import java.util.List;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.mappers.CarMapper;
import com.microservices.microservice1.repos.CarRepo;

public class CustomerOperations implements Operations {
    private final CarRepo carRepo;
    private final CarMapper carMapper;

    public CustomerOperations(CarRepo carRepo, CarMapper carMapper) {
        this.carRepo = carRepo;
        this.carMapper = carMapper;
    }

    @Override
    public List<String> listBrands() {
        return carRepo.listBrands();
    }

    @Override
    public List<String> listModels(String brandName) {
        return carRepo.listModels(brandName);
    }

    @Override
    public List<CarDto> getCars(CarDto targetCar, Integer priceMin, Integer priceMax, Integer yearMin,Integer yearMax) {
        Car target = new Car();
        target = carMapper.dtoToEntity(targetCar);
        System.out.println("Target Car : " + target);

        List<Car> results = carRepo.getCars(target, priceMin, priceMax, yearMin, yearMax);

        List<CarDto> dtoResults = new ArrayList<>();
        for (Car result : results) {
            CarDto carDto;
            carDto = carMapper.entityToDto(result);
            dtoResults.add(carDto);
        }

        return dtoResults;
    }
}
