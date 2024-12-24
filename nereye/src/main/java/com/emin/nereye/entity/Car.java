package com.emin.nereye.entity;

import com.emin.nereye.enumeration.fuel_type;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private fuel_type fuel_type;
    @Column(name = "km")
    private int km;
    @Column(name = "gear_type")
    private boolean gear_type;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private int year;
    @ManyToOne(fetch = FetchType.EAGER, cascade =
            {
                    CascadeType.REFRESH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH,

            })
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade =
                    {
                            CascadeType.REFRESH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.DETACH,
                    })
    @JoinColumn(name = "color_id")
    private Color color;
    // constructors

    public Car() {
    }

    public Car(com.emin.nereye.enumeration.fuel_type fuel_type, int km, boolean gear_type, int capacity, String model, int year) {
        this.fuel_type = fuel_type;
        this.km = km;
        this.gear_type = gear_type;
        this.capacity = capacity;
        this.model = model;
        this.year = year;

    }

    // Getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public fuel_type getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(fuel_type fuel_type) {
        this.fuel_type = fuel_type;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean getGear_type() {
        return gear_type;
    }

    public void setGear_type(boolean gear_type) {
        this.gear_type = gear_type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
