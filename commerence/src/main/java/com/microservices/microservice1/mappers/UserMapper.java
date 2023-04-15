package com.microservices.microservice1.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.entities.UserEntity;

@Component
public class UserMapper {
    @Autowired
    private CarMapper carMapper;

    public UserDto entityToDto(UserEntity user){
        UserDto userDto=new UserDto();
        userDto.setUsername(user.getUsername());
        /*List<CarDto> carDtos=new ArrayList<>();
        for(Car auto:user.getCars()){
            carDtos.add(carMapper.entityToDto(auto));
        }
        userDto.setCars(carDtos);*/
        
        return userDto;
    }
}
