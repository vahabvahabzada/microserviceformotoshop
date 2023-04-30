package com.microservices.microservice1.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.microservices.microservice1.entities.Photo;

import jakarta.transaction.Transactional;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    @Query(value = "select * from photos where car_id=?1",nativeQuery = true)
    public List<Photo> getByCarId(Long carId);

    
    @Transactional
    @Modifying
    @Query(value = "delete from photos where car_id=?1",nativeQuery = true)
    public Integer deleteByCarId(Long carId);
}
