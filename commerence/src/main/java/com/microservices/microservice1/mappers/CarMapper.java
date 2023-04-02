package com.microservices.microservice1.mappers;

import org.springframework.stereotype.Component;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.entities.Car;

@Component
public class CarMapper {
    public Car dtoToEntity(CarDto dto){
        Car entity=new Car();
        entity.setBanStyle(dto.getBanStyle());
        entity.setBarter(dto.getBarter());
        entity.setBrand(dto.getBrand());
        entity.setColor(dto.getColor());
        entity.setCredit(dto.getCredit());
        entity.setCurrency(dto.getCurrency());
        entity.setKilometers(dto.getKilometers());
        entity.setModel(dto.getModel());
        entity.setPrice(dto.getPrice());
        entity.setYear(dto.getYear());
        return entity;
    }
    public CarDto entityToDto(Car entity){
        CarDto dto=new CarDto();
        dto.setBanStyle(entity.getBanStyle());
        dto.setBarter(entity.getBarter());
        dto.setBrand(entity.getBrand());
        dto.setColor(entity.getColor());
        dto.setCredit(entity.getCredit());
        dto.setCurrency(entity.getCurrency());
        dto.setKilometers(entity.getKilometers());
        dto.setModel(entity.getModel());
        dto.setPrice(entity.getPrice());
        dto.setYear(entity.getYear());
        return dto;
    }
}
