package com.microservices.microservice1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.microservices.microservice1.entities.UserEntity;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<UserEntity,Long>{
    public UserEntity findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "delete from users where username=?1",nativeQuery = true)
    public Integer deleteByUsername(String username);
}
