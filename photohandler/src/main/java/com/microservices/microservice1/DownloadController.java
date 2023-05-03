package com.microservices.microservice1;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
    private DownloadUtil downloadUtil;
    public DownloadController(DownloadUtil downloadUtil){
        this.downloadUtil=downloadUtil;
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<?> getPhoto(@RequestBody String fileCode) throws IOException{
        Resource resource=null;
        resource=downloadUtil.getPhoto(fileCode);
        if(resource==null){
            return new ResponseEntity<>("File not found",HttpStatus.NOT_FOUND);
        }

        //return new ResponseEntity<>(resource,HttpStatus.OK);
        return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
        .body(resource);
    }
}
