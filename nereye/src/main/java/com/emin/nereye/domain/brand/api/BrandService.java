package com.emin.nereye.domain.brand.api;
import com.emin.nereye.domain.brand.impl.Brand;
import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    BrandDto findById(int theId);

    void deleteById(int theId);

    Brand save(Brand brand);

    void update(int theId, BrandDto dto);

    BrandDto get(Brand brand);

    List<BrandDto> getAll(List<Brand> brandList);
}
