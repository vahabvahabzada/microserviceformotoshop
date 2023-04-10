package com.microservices.microservice1;

import lombok.Data;

@Data
public class UploadResponse {
    private String fileName;
    private String downloadUrl;
    private long size;
}
