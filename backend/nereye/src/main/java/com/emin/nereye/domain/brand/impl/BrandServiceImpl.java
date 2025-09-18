package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.brand.api.BrandDto;
import com.emin.nereye.domain.brand.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandMapper brandMapper;
    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;

    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = brandRepository.findAll();

        return brandList;
    }

    @Override
    public Brand findById(int theId) {
        Optional<Brand> result = brandRepository.findById(theId);
        Brand b = null;
        if (result.isPresent()) {
            b = result.get();
        } else {
            throw new RuntimeException("Brand has been not found");
        }
        return b;

    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        brandRepository.deleteById(theId);

    }

    @Override
    @Transactional
    public void save(BrandDto brand) {
        brandRepository.save(brandMapper.toBrand(brand));

    }

    @Override
    public BrandDto update(BrandDto dto) {
        Brand brand = brandMapper.toBrand(dto);
        brandRepository.save(brand);
        return brandMapper.toBrandDto(brand);


    }

    @Override
    public BrandDto getBrand(int id) {

        return brandMapper.toBrandDto(findById(id));
    }

    @Override
    public List<BrandDto> getAll(List<Brand> brandList) {
        List<BrandDto> dtoList = new ArrayList<>();
        BrandDto dto = new BrandDto();
        for (Brand b : brandList) {
            dto = brandMapper.toBrandDto(b);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
