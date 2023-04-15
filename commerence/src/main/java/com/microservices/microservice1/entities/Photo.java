package com.microservices.microservice1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
// @Data
@Getter
@Setter
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    private String fileName;
    private String downloadUrl;
    private long size;

    
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car targetCar;
}
