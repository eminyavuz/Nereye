package com.emin.nereye.dto.CarDto;

import com.emin.nereye.entity.Brand;
import com.emin.nereye.entity.Color;
import com.emin.nereye.enumeration.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarReadDto {
    private FuelType fuel_type;
    private int km;
    private boolean gear_type;
    private  int capacity;
    private  String Model;
    private  int year;
    private Brand brand;
    private Color color;
}
