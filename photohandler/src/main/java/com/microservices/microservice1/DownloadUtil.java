package com.microservices.microservice1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
@Component
public class DownloadUtil {
    private Path found;
    public Resource getPhoto(String fileCode) throws IOException{
        try{
        Path filePath=Paths.get("files");
        Files.list(filePath).forEach(photo->{
            if(photo.getFileName().toString().startsWith(fileCode)){
                found=photo;
                return;
            }
        });
        }catch(Exception ex){
            throw new IOException("Path not found",ex);
        }
        if(found!=null){
            return new UrlResource(found.toUri());
        }

        return null;
    }
}
