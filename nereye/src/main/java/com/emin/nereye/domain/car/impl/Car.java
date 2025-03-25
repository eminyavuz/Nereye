package com.emin.nereye.domain.car.impl;

import com.emin.nereye.domain.brand.impl.Brand;
import com.emin.nereye.domain.color.impl.Color;
import com.emin.nereye.enumeration.FuelType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuel_type;
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
    @Column(name = "img_url")
    private String img_url;
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

}
