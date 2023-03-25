package com.microservices.microservice1.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class BlackListDto {
    private String username;
    private String token;
    private Date exp_time;
}
