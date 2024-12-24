package com.emin.nereye.dto;

import com.emin.nereye.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDto {
    private String color_name;
    private String color_code;

    public ColorDto colorToColorDto(Color color){
        ColorDto dto= new ColorDto();
        dto.setColor_code(color.getColor_code());
        dto.setColor_name(color.getColor_name());
return  dto;
    }
    public Color dtoToColor(ColorDto dto,Color color){

        color.setColor_code(dto.getColor_code());
        color.setColor_name(dto.getColor_name());
        return color;
    }

}
