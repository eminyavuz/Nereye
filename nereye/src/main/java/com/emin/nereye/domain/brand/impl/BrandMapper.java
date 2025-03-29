package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.brand.api.BrandDto;
import com.emin.nereye.domain.brand.impl.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(source = "brand_name", target = "brand_name")
    Brand toBrand(BrandDto dto);

    @Mapping(source = "brand_name", target = "brand_name")
    BrandDto toBrandDto(Brand brand);

}
