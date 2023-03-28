package com.microservices.microservice1.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "blacklist")
public class BlackList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long b_id;
	
	private String username;
	private String token;
	private Date exp_time;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User targetUser;
}