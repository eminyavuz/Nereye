package com.emin.nereye.Service;

import com.emin.nereye.dto.BrandDto;
import com.emin.nereye.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand findById(int theId);
    void deleteById(int theId);
    void save(Brand brand);
    void update(int theId,BrandDto dto);
    BrandDto get(Brand brand);
List<BrandDto>  getAll(List<Brand> brandList);
}
