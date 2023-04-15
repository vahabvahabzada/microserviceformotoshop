package com.microservices.microservice1.entities;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Data
@Getter
@Setter
@Table(name = "cars")
public class Car{
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

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity host;


    //@JsonManagedReference
    @OneToMany(mappedBy = "targetCar",cascade = CascadeType.ALL)
    private List<Photo> photos=new ArrayList<>();

    /*@Override
    public String toString(){
        return "Car{'brand='"+brand+"',"+"model='"+model+"',color='"+color+"',price="+price+",currency='"+currency+"',year="+year+",credit="+credit+",barter="+barter+"banStyle='"+banStyle+"',kilometers="+kilometers+",photos="+photos+"}";
    }*/
}