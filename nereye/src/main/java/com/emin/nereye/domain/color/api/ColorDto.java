package com.emin.nereye.domain.color.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ColorDto {
    private String color_name;
    private String color_code;
}
