package com.microservices.microservice1.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.microservices.microservice1.dtos.CarDto;

public class OperationsService {
    private final Operations operations;

    public OperationsService(Operations operations) {
        this.operations = operations;
    }

    public List<String> listBrands() {
        return operations.listBrands();
    }

    public List<String> listModels(String brandName) {
        return operations.listModels(brandName);
    }

    public List<CarDto> getCars(CarDto targetCar, Integer priceMin, Integer priceMax, Integer yearMin,Integer yearMax) {
        return operations.getCars(targetCar, priceMin, priceMax, yearMin, yearMax);
    }

    public Boolean addNewCar(CarDto newCar, List<MultipartFile> fotolar, String targetUser) {
        if (operations instanceof AdminOperations) {
            return ((AdminOperations) operations).addNewCar(newCar, fotolar, targetUser);
        }
        if (operations instanceof SellerOperations) {
            return ((SellerOperations) operations).addNewCar(newCar, fotolar);
        }
        return null;// adi istifadecidirse onsuz da bu metoddan istifade etmek huququ yoxdu
    }
}
