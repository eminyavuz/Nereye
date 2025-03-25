package com.emin.nereye.domain.brand.web;

import com.emin.nereye.domain.brand.api.BrandDto;
import com.emin.nereye.domain.brand.api.BrandService;
import com.emin.nereye.domain.brand.impl.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/save")
    public Brand save(@RequestBody Brand brand) {
        Brand tmpBrand = brandService.save(brand);
        return tmpBrand;
    }

    @GetMapping("/{id}")
    public BrandDto getBrand(@PathVariable int id) {
        return brandService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        brandService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public BrandDto update(@PathVariable int id, BrandDto dto) {
        return brandService.update(id, dto);
    }
}
