package com.emin.nereye.Service;

import com.emin.nereye.dto.BrandDto.BrandReadDto;
import com.emin.nereye.dto.BrandDto.BrandUpdateDto;
import com.emin.nereye.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand findById(int theId);
    void deleteById(int theId);
   Brand save(Brand brand);
    void update(int theId, BrandUpdateDto dto);
    BrandReadDto get(Brand brand);
List<BrandReadDto>  getAll(List<Brand> brandList);
}
