package com.microservices.microservice1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.services.SignUpService;

@RestController
public class SignUpController {
	@Autowired
	SignUpService service;
	
	@PostMapping("/signup")
	public String signUp(@RequestBody UserDto newUser) {
		return service.signUp(newUser);
	}
}