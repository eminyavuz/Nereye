package com.emin.nereye.domain.color.impl;

import com.emin.nereye.domain.car.impl.Car;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"cars"})
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private int color_id;
    @Column(name = "color_name")
    private String color_name;
    @Column(name = "color_code")
    private String color_code;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "color",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE,
            },
            orphanRemoval = true)
    private List<Car> cars;


// Constructors


    public Color() {
    }

    public Color(String color_name, String color_code) {
        this.color_name = color_name;
        this.color_code = color_code;
    }


    // getters and setters
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor_name() {
        return color_name;
    }


    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    // add Car method
    public void addCar(Car car) {
        if (cars == null)
            cars = new ArrayList<>();
        cars.add(car);
        car.setColor(this);
    }
}
