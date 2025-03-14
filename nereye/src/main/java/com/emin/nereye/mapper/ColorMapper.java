package com.emin.nereye.mapper;

import com.emin.nereye.domain.color.api.colorDto.ColorCreateDto;
import com.emin.nereye.domain.color.api.colorDto.ColorReadDto;
import com.emin.nereye.domain.color.api.colorDto.ColorUpdateDto;
import com.emin.nereye.domain.color.impl.Color;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    Color colorCreateDtoToColor(ColorCreateDto dto);

    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    ColorCreateDto colorToColorCreateDto(Color color);

    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    Color colorUpdateDtoToColor(ColorUpdateDto dto);

    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    ColorUpdateDto colorToColorUpdateDto(Color color);

    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    Color colorReadDtoToColor(ColorReadDto dto);

    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    ColorReadDto colorToColorReadDto(Color color);


}
