package com.microservices.microservice1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.microservice1.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Long>{
    public Role findByRolename(String roleName);
}
