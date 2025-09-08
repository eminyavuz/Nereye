package com.emin.nereye.domain.brand.api;
import com.emin.nereye.domain.brand.impl.Brand;
import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findById(int theId);

    void deleteById(int theId);

    void save(BrandDto brand);

    BrandDto update( BrandDto dto);

    BrandDto getBrand(int id);

    List<BrandDto> getAll(List<Brand> brandList);
}
