package com.microservices.microservice1.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.microservice1.entities.Car;

public interface CarRepo extends JpaRepository<Car,Long>{
    
    @Query(value = "select brand from cars",nativeQuery=true)
    public List<String> listBrands();

    @Query(value = "select model from cars where brand=?",nativeQuery = true)
    public List<String> listModels(String carRepo);
    
    /*@Query(value="(select * from cars where "+
    "brand=:#{#car.brand} or not exists(select * from cars where brand=:#{#car.brand} or :#{#car.brand} <>''))" + " intersect "+
    "(select * from cars where model=:#{#car.model} or not exists(select * from cars where model=:#{#car.model} or :#{#car.model} <>''))"+" intersect "+
    "(select * from cars where color=:#{#car.color} or not exists(select * from cars where color=:#{#car.color} or :#{#car.color} <>''))"+" intersect "+
    "(select * from cars where barter=:#{#car.barter} or not exists(select * from cars where barter=:#{#car.barter} or :#{#car.barter} <>''))",nativeQuery=true)*/
    
    @Query("(select item from Car item where item.brand=:#{#car.brand} or not exists(select item from Car item where :#{#car.brand} <>''))" + " intersect "+
    "(select item from Car item where item.model=:#{#car.model} or not exists(select item from Car item where :#{#car.model} <>''))"+" intersect "+
    "(select item from Car item where item.color=:#{#car.color} or not exists(select item from Car item where :#{#car.color} <>''))"+" intersect "+
    "(select item from Car item where item.banStyle=:#{#car.banStyle} or not exists(select item from Car item where :#{#car.banStyle} <>''))"+" intersect "+
    "(select item from Car item where item.year=:#{#car.year} or not exists(select item from Car item where :#{#car.year} is not null))"+" intersect "+
    "(select item from Car item where item.credit=:#{#car.credit} or not exists(select item from Car item where :#{#car.credit} is not null))"+" intersect "+
    "(select item from Car item where item.currency=:#{#car.currency} or not exists(select item from Car item where :#{#car.currency} <>''))"+" intersect "+
    "(select item from Car item where item.kilometers=:#{#car.kilometers} or not exists(select item from Car item where :#{#car.kilometers} is not null))"+" intersect "+
    "(select item from Car item where item.price=:#{#car.price} or not exists(select item from Car item where :#{#car.price} is not null))"+" intersect "+
    "(select item from Car item where item.barter=:#{#car.barter} or not exists(select item from Car item where :#{#car.barter} is not null))")
    public List<Car> getCars(@Param("car") Car target);
}