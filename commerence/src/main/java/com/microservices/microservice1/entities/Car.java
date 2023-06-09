package com.microservices.microservice1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
// @Data
@Getter
@Setter
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity host;

    @OneToMany(mappedBy = "targetCar", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

}