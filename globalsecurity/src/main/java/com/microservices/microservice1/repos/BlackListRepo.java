package com.microservices.microservice1.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.microservice1.entities.BlackList;

public interface BlackListRepo extends JpaRepository<BlackList, Long>{
    public BlackList findByToken(String token);
    public List<BlackList> findByUsername(String username);
}
