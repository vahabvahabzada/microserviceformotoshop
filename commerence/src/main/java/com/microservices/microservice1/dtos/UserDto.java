package com.microservices.microservice1.dtos;

import java.util.List;

import com.microservices.microservice1.entities.RoleDto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private List<RoleDto> roles;
}
