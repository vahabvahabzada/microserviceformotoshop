package com.microservices.microservice1.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//@Data
public class PhotoDto {
    private Long photoId;

    private String fileName;
    private String downloadUrl;
    private long size;

    //@JsonBackReference
    private CarDto targetCarDto;
}
