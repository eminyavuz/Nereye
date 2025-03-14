package com.emin.nereye.domain.color.api.colorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorUpdateDto {
    private String color_name;
    private String color_code;

}
