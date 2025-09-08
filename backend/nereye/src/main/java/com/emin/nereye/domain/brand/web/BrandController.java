package com.emin.nereye.domain.brand.web;

import com.emin.nereye.domain.brand.api.BrandDto;
import com.emin.nereye.domain.brand.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/save")
    public void save(@RequestBody BrandDto brand) {
         brandService.save(brand);

    }

    @GetMapping("/getAll")
    public List<BrandDto> getAll() {
        return brandService.getAll(brandService.findAll());
    }

    @GetMapping("/{id}")
    public BrandDto getBrand(@PathVariable int id) {
        return brandService.getBrand(id);
    }

    @GetMapping("/get/{id}")
    public BrandDto getBrandAlias(@PathVariable int id) {
        return brandService.getBrand(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        brandService.deleteById(id);
    }

    @PutMapping("/update")
    public BrandDto update(@RequestBody BrandDto dto) {
        return brandService.update( dto);
    }
}
