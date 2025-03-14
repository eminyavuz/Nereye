package com.emin.nereye.domain.car.api.carDto;

import com.emin.nereye.domain.brand.impl.Brand;
import com.emin.nereye.domain.color.impl.Color;
import com.emin.nereye.enumeration.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarReadDto {
    private FuelType fuel_type;
    private int km;
    private boolean gear_type;
    private int capacity;
    private String Model;
    private int year;
    private Brand brand;
    private Color color;
}
