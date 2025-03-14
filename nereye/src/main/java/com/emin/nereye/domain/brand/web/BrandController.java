package com.emin.nereye.domain.brand.web;

import com.emin.nereye.domain.brand.impl.Brand;
import com.emin.nereye.domain.brand.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
