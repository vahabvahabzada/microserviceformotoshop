package com.microservices.microservice1.mappers;

import org.springframework.stereotype.Component;

import com.microservices.microservice1.dtos.PhotoDto;
import com.microservices.microservice1.entities.Photo;

@Component
public class PhotoMapper {
    public PhotoDto entityToDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setPhotoId(photo.getPhotoId());
        photoDto.setFileName(photo.getFileName());
        photoDto.setDownloadUrl(photo.getDownloadUrl());
        photoDto.setSize(photo.getSize());
        // photoDto.setTargetCarDto(carMapper.entityToDto(photo.getTargetCar()));
        return photoDto;
    }
}
