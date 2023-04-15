package com.microservices.microservice1.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.microservices.microservice1.entities.Photo;
import com.microservices.microservice1.entities.UserEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
//@EqualsAndHashCode(exclude = "photos")
public class CarDto {
    //private Long carId;
    private String brand;
    private String model;
    private String color;
    private Integer price;
    private String currency;
    private Integer year;
    private Boolean credit;
    private Boolean barter;
    private String banStyle;
    private Integer kilometers;

    //private List<MultipartFile> photos;
    
    
    //@JsonManagedReference
    private List<PhotoDto> photos=new ArrayList<>();

    //@JsonBackReference
    private UserDto host;

    /*@Override
    public String toString(){
        return "CarDto{model='"+model+"',brand='"+brand+"',color='"+color+"',price="+price+",currency='"+currency+"',year="+year+",credit="+credit+",barter="+barter+"banStyle='"+banStyle+"',kilometers="+kilometers+",photos="+photos+",host="+host+"}";
    }*/
}
