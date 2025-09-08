package com.emin.nereye.domain.color.impl;

import com.emin.nereye.domain.color.api.ColorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    @Mapping(source = "color_id", target = "color_id")
    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    @Mapping(target = "cars", ignore = true)
    Color toColor(ColorDto dto);

    @Mapping(source = "color_id", target = "color_id")
    @Mapping(source = "color_name", target = "color_name")
    @Mapping(source = "color_code", target = "color_code")
    ColorDto toColorDto(Color color);

}
