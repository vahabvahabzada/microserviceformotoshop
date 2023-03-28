package com.microservices.microservice1.communication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice1.jwt.JWTGenerator;

@RestController
public class JWTController {
	@Autowired
	private JWTGenerator jwtGenerator;
	
	/*@PostMapping("/generateToken")
	public String generateToken(@RequestBody Authentication authentication) {
		return jwtGenerator.genetateToken(authentication);
	}*/
	
	@PostMapping("/validatetoken")
	public Boolean validateToken(@RequestBody String token) {
		return jwtGenerator.validateToken(token);
	}
	
	@PostMapping("/getusername")
	public String getUsernameFromToken(@RequestBody String token) {
		return jwtGenerator.getUsernameFromToken(token);
	}
	
	@PostMapping("/getexpdate")
	public Date getExpiDate(@RequestBody String token) {
		return jwtGenerator.getExpireDate(token);
	}
}
