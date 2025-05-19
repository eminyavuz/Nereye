package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.brand.api.BrandDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T20:39:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand toBrand(BrandDto dto) {
        if ( dto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setBrand_name( dto.getBrand_name() );

        return brand;
    }

    @Override
    public BrandDto toBrandDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setBrand_name( brand.getBrand_name() );

        return brandDto;
    }
}
