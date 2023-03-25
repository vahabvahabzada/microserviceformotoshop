package com.microservices.microservice1.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.entities.Role;
import com.microservices.microservice1.entities.User;
import com.microservices.microservice1.repos.RoleRepo;
import com.microservices.microservice1.repos.UsersRepo;

@Service
public class SignUpService {
	@Autowired
	UsersRepo repo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	PasswordEncoder encoder;

	public String signUp(UserDto newUser) {
		User user=new User();
	user.setUsername(newUser.getUsername());
	user.setPassword(encoder.encode(newUser.getPassword()));
		if(repo.findByUsername(user.getUsername())!=null) {
			return "failure";
		}
		Role role=roleRepo.findByRolename("USER");
		user.setRoles(Collections.singletonList(role));
		repo.save(user);
		return "success";
	}
}