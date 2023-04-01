package com.microservices.microservice1.dtos;

import lombok.Data;

@Data
public class GetCars {
    private CarDto carDto;
    private Integer yearMin;
    private Integer yearMax;
    private Integer priceMin;
    private Integer priceMax;
}
