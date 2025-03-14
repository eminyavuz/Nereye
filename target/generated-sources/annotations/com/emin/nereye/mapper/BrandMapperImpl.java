package com.emin.nereye.mapper;

import com.emin.nereye.domain.brand.api.brandDto.BrandCreateDto;
import com.emin.nereye.domain.brand.api.brandDto.BrandReadDto;
import com.emin.nereye.domain.brand.api.brandDto.BrandUpdateDto;
import com.emin.nereye.domain.brand.impl.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-14T20:46:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand brandCreateDtoToBrand(BrandCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setBrand_name( dto.getBrand_name() );

        return brand;
    }

    @Override
    public BrandCreateDto brandToBrandCreateDto(Brand dto) {
        if ( dto == null ) {
            return null;
        }

        BrandCreateDto brandCreateDto = new BrandCreateDto();

        brandCreateDto.setBrand_name( dto.getBrand_name() );

        return brandCreateDto;
    }

    @Override
    public BrandReadDto brandToBrandReadDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandReadDto brandReadDto = new BrandReadDto();

        brandReadDto.setBrand_name( brand.getBrand_name() );

        return brandReadDto;
    }

    @Override
    public Brand brandReadDtoToBrand(BrandReadDto dto) {
        if ( dto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setBrand_name( dto.getBrand_name() );

        return brand;
    }

    @Override
    public BrandUpdateDto brandToBrandUpdateDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandUpdateDto brandUpdateDto = new BrandUpdateDto();

        brandUpdateDto.setBrand_name( brand.getBrand_name() );

        return brandUpdateDto;
    }

    @Override
    public Brand brandUpdateDtoToBrand(BrandUpdateDto brand) {
        if ( brand == null ) {
            return null;
        }

        Brand brand1 = new Brand();

        brand1.setBrand_name( brand.getBrand_name() );

        return brand1;
    }
}
