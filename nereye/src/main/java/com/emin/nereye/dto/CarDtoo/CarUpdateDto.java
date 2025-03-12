package com.emin.nereye.dto.CarDtoo;

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
public class CarUpdateDto {

    private int km;
    private  int capacity;
    private Color color;
}
