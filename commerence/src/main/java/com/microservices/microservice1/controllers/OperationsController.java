package com.microservices.microservice1.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.microservices.microservice1.dtos.CarDto;
import com.microservices.microservice1.dtos.GetCars;
import com.microservices.microservice1.mappers.CarMapper;
import com.microservices.microservice1.repos.CarRepo;
import com.microservices.microservice1.repos.PhotoRepo;
import com.microservices.microservice1.repos.UserRepo;
import com.microservices.microservice1.services.AdminOperations;
import com.microservices.microservice1.services.CustomerOperations;
import com.microservices.microservice1.services.OperationsService;
import com.microservices.microservice1.services.SellerOperations;

@RestController
public class OperationsController {

    private OperationsService operationsService; 
    private final CarRepo carRepo;
    private final CarMapper carMapper;
    private final UserRepo userRepo;
    private final PhotoRepo photoRepo;

    private final RestTemplate restTemplate;

    public OperationsController(CarRepo carRepo,CarMapper carMapper,UserRepo userRepo,PhotoRepo photoRepo,RestTemplate restTemplate){
        this.carRepo=carRepo;
        this.carMapper=carMapper;
        this.userRepo=userRepo;
        this.photoRepo=photoRepo;
        this.restTemplate=restTemplate;
    }

    @GetMapping("/listbrands")
    public List<String> listBrands() {
        operationsService=operationManager(carRepo, carMapper, userRepo, photoRepo, restTemplate);

        return operationsService.listBrands();
    }// lists all options when cliked on brands option in UI


    @GetMapping("/listmodels")
    public List<String> listModels(@RequestBody String brandName) {
        operationsService=operationManager(carRepo, carMapper, userRepo, photoRepo, restTemplate);
        return operationsService.listModels(brandName);
    }

    @GetMapping("/getcars")
    public List<CarDto> getCars(@RequestBody GetCars data) {// bir secime click edednde,uygun olaraq filterleyib neticeleri cixarir ve elecede qiymet ve il araligini teyin edende
        operationsService=operationManager(carRepo, carMapper, userRepo, photoRepo, restTemplate);
        System.out.println("/getcars is activated : data="+data.toString());
        return operationsService.getCars(data.getCarDto(), data.getPriceMin(), data.getPriceMax(), data.getYearMin(),data.getYearMax());
    }

    @PostMapping(value = "/addnewcar", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public Boolean addNewCar( @RequestPart("car") CarDto newCar, @RequestPart("file") List<MultipartFile> photos, @RequestPart("targetuser") String targetUser) {
        System.out.println("AddController.java -->Active");
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("USER"))){
            return false;
        }

        operationsService=operationManager(carRepo, carMapper, userRepo, photoRepo, restTemplate);
        return operationsService.addNewCar(newCar, photos, targetUser);
    }

    public OperationsService operationManager(CarRepo carRepo,CarMapper carMapper,UserRepo userRepo,PhotoRepo photoRepo,RestTemplate restTemplate){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            return new OperationsService(new AdminOperations(carRepo, carMapper, userRepo, photoRepo, restTemplate));
        }
        
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("SELLER"))){
            return new OperationsService(new SellerOperations(carRepo, carMapper, userRepo, photoRepo, restTemplate));
        }

        return new OperationsService(new CustomerOperations(carRepo, carMapper));
    }
}