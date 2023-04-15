package com.microservices.microservice1.mappers;

import org.springframework.stereotype.Component;

import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.entities.UserEntity;

@Component
public class UserMapper {
    public UserDto entityToDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());

        /*
         * List<CarDto> carDtos=new ArrayList<>();
         * for(Car auto:user.getCars()){
         * carDtos.add(carMapper.entityToDto(auto));
         * }
         * userDto.setCars(carDtos);
         */

        return userDto;
    }
}
