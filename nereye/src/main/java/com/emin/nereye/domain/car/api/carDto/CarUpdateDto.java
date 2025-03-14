package com.emin.nereye.domain.car.api.carDto;

import com.emin.nereye.domain.color.impl.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarUpdateDto {

    private int km;
    private int capacity;
    private Color color;
}
