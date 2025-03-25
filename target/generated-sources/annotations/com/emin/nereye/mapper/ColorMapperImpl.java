package com.emin.nereye.mapper;

import com.emin.nereye.domain.color.api.ColorDto;
import com.emin.nereye.domain.color.impl.Color;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T05:26:59+0300",
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
