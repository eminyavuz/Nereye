package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.brand.api.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "brand_name", target = "brand_name")
    @Mapping(target = "cars", ignore = true)
    Brand toBrand(BrandDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "brand_name", target = "brand_name")
    BrandDto toBrandDto(Brand brand);

}
