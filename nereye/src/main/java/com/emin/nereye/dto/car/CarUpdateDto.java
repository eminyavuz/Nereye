package com.emin.nereye.dto.CarDto;

import com.emin.nereye.entity.Color;
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
