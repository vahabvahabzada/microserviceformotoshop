package com.microservices.microservice1.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.entities.Role;
import com.microservices.microservice1.entities.User;
import com.microservices.microservice1.repos.RoleRepo;
import com.microservices.microservice1.repos.UsersRepo;

@Service
public class SignUpService {
	@Autowired
	private UsersRepo repo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RestTemplate restTemplate;

	public String signUp(UserDto newUser) {
		User user=new User();
	user.setUsername(newUser.getUsername());
	user.setPassword(encoder.encode(newUser.getPassword()));
		if(repo.findByUsername(user.getUsername())!=null) {
			return "failure";
		}
		Role role=roleRepo.findByRolename("USER");
		//user.setRoles(Collections.singletonList(role));
		user.setRoles(Collections.singleton(role));
		repo.save(user);

		System.out.println("SignUpService --> Username is :"+newUser.getUsername());

		restTemplate.postForObject("http://localhost:8082/saveuser", newUser.getUsername(), Boolean.class);
		return "success";
	}
}