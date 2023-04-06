package com.microservices.microservice1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.microservice1.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Long>{
    public UserEntity findByUsername(String username);
}
