package com.microservices.microservice1.dtos;

//import java.util.List;
import java.util.Set;

import com.microservices.microservice1.entities.Role;

import lombok.Data;

@Data
public class UserDto {
	private String username;
	private String password;
	private Set<Role> roles;
}
