package com.microservices.microservice1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.microservices.microservice1.entities.User;

import jakarta.transaction.Transactional;

public interface UsersRepo extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	public Integer deleteByUsername(String username);

	@Transactional
	@Modifying
	@Query(value ="delete from user_roles where user_id=?1 and role_id=?2",nativeQuery = true)
	public void deleteRelation(Long userId,Long roleId);
}
