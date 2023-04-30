package com.microservices.microservice1.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.microservice1.entities.Car;

import jakarta.transaction.Transactional;

public interface CarRepo extends JpaRepository<Car, Long> {

    @Query("select item.brand from Car item")
    public List<String> listBrands();

    @Query("select item.model from Car item where item.brand=?1")
    public List<String> listModels(String carRepo);

    @Query("(select item from Car item where item.brand=:#{#car.brand} or not exists(select item from Car item where :#{#car.brand} <>''))"
            + " intersect " +
            "(select item from Car item where item.model=:#{#car.model} or not exists(select item from Car item where :#{#car.model} <>''))"
            + " intersect " +
            "(select item from Car item where item.color=:#{#car.color} or not exists(select item from Car item where :#{#car.color} <>''))"
            + " intersect " +
            "(select item from Car item where item.banStyle=:#{#car.banStyle} or not exists(select item from Car item where :#{#car.banStyle} <>''))"
            + " intersect " +
            "(select item from Car item where item.year between :yearmin and :yearmax or not exists(select item from Car item where (:yearmin is not null and :yearmin > item.year) or (:yearmax is not null and :yearmax < item.year)))"
            + " intersect " +
            "(select item from Car item where item.credit=:#{#car.credit} or not exists(select item from Car item where :#{#car.credit} is not null))"
            + " intersect " +
            "(select item from Car item where item.currency=:#{#car.currency} or not exists(select item from Car item where :#{#car.currency} <>''))"
            + " intersect " +
            "(select item from Car item where item.kilometers=:#{#car.kilometers} or not exists(select item from Car item where :#{#car.kilometers} is not null))"
            + " intersect " +
            "(select item from Car item where item.price between :pricemin and :pricemax or not exists(select item from Car item where (:pricemin is not null and :pricemin > item.price) or (:pricemax is not null and :pricemax <item.price)))"
            + " intersect " +
            "(select item from Car item where item.barter=:#{#car.barter} or not exists(select item from Car item where :#{#car.barter} is not null))")
    public List<Car> getCars(@Param("car") Car target, @Param("pricemin") Integer priceMin, @Param("pricemax") Integer priceMax, @Param("yearmin") Integer yearMin, @Param("yearmax") Integer yearMax);


    @Query(value = "select * from cars where user_id=?1",nativeQuery = true)
    public List<Car> getCarsByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from cars where user_id=?1",nativeQuery = true)
    public Integer deleteByUserId(Long userId);
}