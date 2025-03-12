package com.emin.nereye.mapper;

import com.emin.nereye.dto.ColorDto.ColorCreateDto;
import com.emin.nereye.dto.ColorDto.ColorReadDto;
import com.emin.nereye.dto.ColorDto.ColorUpdateDto;
import com.emin.nereye.entity.Color;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-12T17:53:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ColorMapperImpl implements ColorMapper {

    @Override
    public Color colorCreateDtoToColor(ColorCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Color color = new Color();

        color.setColor_name( dto.getColor_name() );
        color.setColor_code( dto.getColor_code() );

        return color;
    }

    @Override
    public ColorCreateDto colorToColorCreateDto(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorCreateDto colorCreateDto = new ColorCreateDto();

        colorCreateDto.setColor_name( color.getColor_name() );
        colorCreateDto.setColor_code( color.getColor_code() );

        return colorCreateDto;
    }

    @Override
    public Color colorUpdateDtoToColor(ColorUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Color color = new Color();

        color.setColor_name( dto.getColor_name() );
        color.setColor_code( dto.getColor_code() );

        return color;
    }

    @Override
    public ColorUpdateDto colorToColorUpdateDto(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorUpdateDto colorUpdateDto = new ColorUpdateDto();

        colorUpdateDto.setColor_name( color.getColor_name() );
        colorUpdateDto.setColor_code( color.getColor_code() );

        return colorUpdateDto;
    }

    @Override
    public Color colorReadDtoToColor(ColorReadDto dto) {
        if ( dto == null ) {
            return null;
        }

        Color color = new Color();

        color.setColor_name( dto.getColor_name() );
        color.setColor_code( dto.getColor_code() );

        return color;
    }

    @Override
    public ColorReadDto colorToColorReadDto(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorReadDto colorReadDto = new ColorReadDto();

        colorReadDto.setColor_name( color.getColor_name() );
        colorReadDto.setColor_code( color.getColor_code() );

        return colorReadDto;
    }
}
