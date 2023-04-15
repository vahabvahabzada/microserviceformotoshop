package com.microservices.microservice1.dtos;

import java.util.ArrayList;
import java.util.List;


//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class CarDto {
    // private Long carId;
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

    // private List<MultipartFile> photos;

    
    private List<PhotoDto> photos = new ArrayList<>();

    
    private UserDto host;
}
