package com.microservices.microservice1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    private UploadUtil uploadUtil;

    @PostMapping("/uploadphoto")
    public ResponseEntity<List<UploadResponse>> uploadPhoto(@RequestPart("file") List<MultipartFile> multipartFiles) throws IOException {
        List<UploadResponse> uploadResponses = new ArrayList<>();
        System.out.println("UploadController.java --> "+multipartFiles.get(0).getOriginalFilename());
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            long size = multipartFile.getSize();

            String fileCode = uploadUtil.uploadPhoto(fileName, multipartFile);
            UploadResponse response = new UploadResponse();
            response.setFileName(fileName);
            response.setSize(size);
            response.setDownloadUrl("/downloadFile/" + fileCode);

            uploadResponses.add(response);
        }
        //return uploadResponses;
        return new ResponseEntity<>(uploadResponses,HttpStatus.OK);
    }
}
