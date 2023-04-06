package com.microservices.microservice1.dtos;

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
}
