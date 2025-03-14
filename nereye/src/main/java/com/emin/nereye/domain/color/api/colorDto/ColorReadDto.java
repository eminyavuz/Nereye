package com.emin.nereye.domain.color.api.colorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ColorReadDto {
    private String color_name;
    private String color_code;

}
