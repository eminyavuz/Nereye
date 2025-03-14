package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.car.impl.Car;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer id;

    @Column(name = "brand_name")
    private String brand_name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
    },
            orphanRemoval = true)
    private List<Car> cars;

    // Constructors


    public Brand() {
    }

    public Brand(String brand_name) {
        this.brand_name = brand_name;
    }

    // getters and setters

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    // add Brand
    public void addCar(Car car) {
        if (cars == null)
            cars = new ArrayList<>();
        cars.add(car);
        car.setBrand(this);
    }
}
