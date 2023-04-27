package com.microservices.microservice1.services;

import java.util.List;
import com.microservices.microservice1.dtos.CarDto;

public interface Operations {
    public List<String> listBrands();

    public List<String> listModels(String brandName);

    public List<CarDto> getCars(CarDto targetCar, Integer priceMin, Integer priceMax, Integer yearMin, Integer yearMax);
}
