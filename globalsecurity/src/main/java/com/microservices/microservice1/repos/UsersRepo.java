package com.microservices.microservice1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.microservice1.entities.User;

public interface UsersRepo extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	public Integer deleteByUsername(String username);
}
