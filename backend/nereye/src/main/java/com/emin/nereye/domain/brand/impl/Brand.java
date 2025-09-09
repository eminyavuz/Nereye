package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.car.impl.Car;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"cars"})
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


    public Brand(String brand_name) {
        this.brand_name = brand_name;
    }

    {
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
