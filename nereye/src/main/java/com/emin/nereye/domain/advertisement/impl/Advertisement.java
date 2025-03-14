package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.car.impl.Car;
import jakarta.persistence.*;

@Entity
@Table(name = "ad")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private int ad_id;
    @Column(name = "daily_price")
    private int daily_price;
    @Column(name = "location")
    private String location;
    @Column(name = "deposit")
    private int deposit;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
            })
    private Car car;

    // Contsturctors


    public Advertisement(int daily_price,
                         String location,
                         int deposit,
                         Car car) {
        this.daily_price = daily_price;
        this.location = location;
        this.deposit = deposit;
        this.car = car;
    }

    public Advertisement() {
    }

    // getters and setters
    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(int daily_price) {
        this.daily_price = daily_price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
