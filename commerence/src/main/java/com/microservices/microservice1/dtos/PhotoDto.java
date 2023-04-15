package com.microservices.microservice1.dtos;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// @Data
public class PhotoDto {
    private Long photoId;

    private String fileName;
    private String downloadUrl;
    private long size;

    // private CarDto targetCarDto;
}
