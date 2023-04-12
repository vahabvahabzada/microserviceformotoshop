package com.microservices.microservice1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.dtos.UploadResponse;
import com.microservices.microservice1.entities.Car;
import com.microservices.microservice1.entities.Photo;
import com.microservices.microservice1.mappers.CarMapper;
import com.microservices.microservice1.repos.CarRepo;
import com.microservices.microservice1.repos.PhotoRepo;
import com.microservices.microservice1.repos.UserRepo;


@Service
public class AddCarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PhotoRepo photoRepo;

    public Boolean addNewCar(CarDto newCar,List<MultipartFile> fotolar){
        System.out.println("Car-->"+newCar.getBrand());
        System.out.println("fotolar-->"+fotolar.get(0).getOriginalFilename());

        Car car=new Car();
        car=carMapper.dtoToEntity(newCar);
        car.setHost(userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        carRepo.save(car);


        MultiValueMap<String,Object> bodyMap=new LinkedMultiValueMap<>();
        bodyMap.add("file",fotolar.get(0).getResource());
        

        ResponseEntity<List<UploadResponse>> responses=restTemplate.exchange("http://localhost:8082/uploadphoto", HttpMethod.POST,new HttpEntity<>(bodyMap/*,headers*/),new ParameterizedTypeReference<List<UploadResponse>>(){});
        
        System.out.println(responses.toString());//yoxlama


        for(UploadResponse response:responses.getBody()){
            Photo foto=new Photo();
            foto.setFileName(response.getFileName());
            foto.setDownloadUrl(response.getDownloadUrl());
            foto.setSize(response.getSize());

            foto.setTargetCar(car);
            photoRepo.save(foto);
        }
        
        return true; // yuxaridaki emeliyyatlarda problem olsa kod hec bura cata bilmeyecek,demeli emeliyyat ugurludusa true-du
    }
}