package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.car.impl.Car;
import com.emin.nereye.domain.user.impl.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "ad")
@JsonIgnoreProperties({"owner", "tenet"})
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenet_id", referencedColumnName = "id")
    private User tenet;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    // Contsturctors


    public Advertisement(int daily_price,
                         String location,
                         int deposit,
                         Car car, User owner_id, User tenet_id) {
        this.daily_price = daily_price;
        this.location = location;
        this.deposit = deposit;
        this.car = car;
        this.owner = owner_id;
        this.tenet = tenet_id;
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


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getTenet() {
        return tenet;
    }

    public void setTenet(User tenet) {
        this.tenet = tenet;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
