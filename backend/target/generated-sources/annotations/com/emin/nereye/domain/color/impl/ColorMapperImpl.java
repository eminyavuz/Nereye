package com.emin.nereye.domain.color.impl;

import com.emin.nereye.domain.color.api.ColorDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T20:39:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ColorMapperImpl implements ColorMapper {

    @Override
    public Color toColor(ColorDto dto) {
        if ( dto == null ) {
            return null;
        }

        Color color = new Color();

        color.setColor_name( dto.getColor_name() );
        color.setColor_code( dto.getColor_code() );

        return color;
    }

    @Override
    public ColorDto toColorDto(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorDto colorDto = new ColorDto();

        colorDto.setColor_name( color.getColor_name() );
        colorDto.setColor_code( color.getColor_code() );

        return colorDto;
    }
}
