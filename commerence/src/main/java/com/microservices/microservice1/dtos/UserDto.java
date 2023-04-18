package com.microservices.microservice1.dtos;

//import java.util.ArrayList;
import java.util.List;


//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;

    private List<RoleDto> roles;


    //private List<CarDto> cars = new ArrayList<>();
}
