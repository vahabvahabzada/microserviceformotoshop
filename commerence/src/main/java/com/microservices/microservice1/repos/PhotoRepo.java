package com.microservices.microservice1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.microservice1.entities.Photo;

public interface PhotoRepo extends JpaRepository<Photo,Long>{
}
