package com.microservices.microservice1.services;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
import com.microservices.microservice1.mappers.CarMapper;
import com.microservices.microservice1.repos.CarRepo;
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

    public Boolean addNewCar(CarDto newCar,List<MultipartFile> fotolar) {
        System.out.println("Car-->"+newCar.getBrand());
        System.out.println("fotolar-->"+fotolar.get(0).getOriginalFilename());

        Car car=new Car();
        car=carMapper.dtoToEntity(newCar);
        car.setHost(userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        carRepo.save(car);


        MultiValueMap<String,Object> bodyMap=new LinkedMultiValueMap<>();
        //System.out.println("URI-->"+fotolar.get(0));
        bodyMap.add("file", new PathResource("C:/Users/Vahab/Pictures/walpaper.png"));
        bodyMap.add("file", "C:/Users/Vahab/Pictures/Pictures/Capture.png");
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String,Object>> requestEntity=new HttpEntity<MultiValueMap<String,Object>>(bodyMap,headers);
        //MultiValueMap<String,Object> body=new LinkedMultiValueMap<>();
        //body.add("file",fotolar.get(0));
        //HttpEntity<MultiValueMap<String,Object>> requestEntity=new HttpEntity<MultiValueMap<String,Object>>(body,headers);
        
        /*MultiValueMap<String,String> fileMap=new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition=ContentDisposition.builder("form-data").name("file").filename(fotolar.get(0).getOriginalFilename()).build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        */
        
        ResponseEntity<List<UploadResponse>> response=restTemplate.exchange("http://localhost:8082/uploadphoto", HttpMethod.POST,requestEntity,new ParameterizedTypeReference<List<UploadResponse>>(){});

        //ResponseEntity<UploadResponse> responses=restTemplate.postForEntity("http://localhost:8082/uploadphoto", requestEntity, UploadResponse.class);
        
        //System.out.println(responses.getBody().getFileName());
        
        System.out.println(response.toString());
        return true; // yuxaridaki emeliyyatlarda problem olsa kod hec bura cata bilmeyecek,demeli emeliyyat ugurludusa true-du
    }
}
