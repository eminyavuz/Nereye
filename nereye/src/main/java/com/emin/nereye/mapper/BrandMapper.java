package com.emin.nereye.mapper;

import com.emin.nereye.dto.BrandDtoo.BrandCreateDto;
import com.emin.nereye.dto.BrandDtoo.BrandReadDto;
import com.emin.nereye.dto.BrandDtoo.BrandUpdateDto;
import com.emin.nereye.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(source = "brand_name",target = "brand_name")
    Brand brandCreateDtoToBrand (BrandCreateDto dto);
    @Mapping(source = "brand_name",target = "brand_name")
    BrandCreateDto brandToBrandCreateDto (Brand dto);
    @Mapping(source = "brand_name",target = "brand_name")
    BrandReadDto brandToBrandReadDto(Brand brand);
    @Mapping(source = "brand_name",target = "brand_name")
    Brand brandReadDtoToBrand (BrandReadDto dto);
    @Mapping(source = "brand_name",target = "brand_name")
    BrandUpdateDto brandToBrandUpdateDto(Brand brand);
    @Mapping(source = "brand_name",target = "brand_name")
    Brand brandUpdateDtoToBrand(BrandUpdateDto brand);

}
