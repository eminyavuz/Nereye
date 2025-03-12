package com.emin.nereye.controller;

import com.emin.nereye.Service.BrandService;
import com.emin.nereye.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;
   @Autowired
    public BrandController(BrandService brandService){
        this.brandService=brandService;
    }

    @PostMapping("/save")
    public Brand save(@RequestBody Brand brand){

       Brand tmpBrand= brandService.save(brand);
       return tmpBrand;

    }
}
