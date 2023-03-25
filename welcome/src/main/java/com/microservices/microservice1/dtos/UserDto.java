package com.microservices.microservice1.dtos;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private List<RoleDto> roles;
}
