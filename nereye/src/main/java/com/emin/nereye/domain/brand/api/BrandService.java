package com.emin.nereye.domain.brand.api;

import com.emin.nereye.domain.brand.api.brandDto.BrandReadDto;
import com.emin.nereye.domain.brand.api.brandDto.BrandUpdateDto;
import com.emin.nereye.domain.brand.impl.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findById(int theId);

    void deleteById(int theId);

    Brand save(Brand brand);

    void update(int theId, BrandUpdateDto dto);

    BrandReadDto get(Brand brand);

    List<BrandReadDto> getAll(List<Brand> brandList);
}
