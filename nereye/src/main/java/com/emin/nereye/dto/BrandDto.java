package com.emin.nereye.dto;

import com.emin.nereye.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private String brand_name;

    public BrandDto BrandToBrandDto (Brand brand){
        BrandDto dto= new BrandDto();
        dto.setBrand_name(brand.getBrand_name());
        return dto;
    }
    public Brand dtoToBrand(Brand brand, BrandDto dto){

        brand.setBrand_name(dto.getBrand_name());
        return brand;
    }
}
