package com.microservices.microservice1.dtos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.microservices.microservice1.entities.Photo;
import com.microservices.microservice1.entities.UserEntity;

import lombok.Data;

@Data
public class CarDto {
    //private Long carId;
    private String brand;
    private String model;
    private String color;
    private Integer price;
    private String currency;
    private Integer year;
    private Boolean credit;
    private Boolean barter;
    private String banStyle;
    private Integer kilometers;

    //private List<MultipartFile> photos;
    
    private List<Photo> photos;
    //private UserEntity host;
}
