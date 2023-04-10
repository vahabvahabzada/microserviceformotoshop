package com.microservices.microservice1.dtos;

import lombok.Data;

@Data
public class UploadResponse {
    private String fileName;
    private String downloadUrl;
    private long size;
}