package com.emin.nereye.domain.brand.impl;

import com.emin.nereye.domain.brand.api.BrandService;
import com.emin.nereye.domain.brand.api.brandDto.BrandReadDto;
import com.emin.nereye.domain.brand.api.brandDto.BrandUpdateDto;
import com.emin.nereye.mapper.BrandMapper;
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
    public Brand save(Brand brand) {
        return brandRepository.save(brand);

    }

    @Override
    public void update(int theId, BrandUpdateDto dto) {
        Brand brand = findById(theId);
        brand = brandMapper.brandUpdateDtoToBrand(dto);
        brandRepository.save(brand);


    }

    @Override
    public BrandReadDto get(Brand brand) {

        return brandMapper.brandToBrandReadDto(brand);
    }

    @Override
    public List<BrandReadDto> getAll(List<Brand> brandList) {
        List<BrandReadDto> dtoList = new ArrayList<>();
        BrandReadDto dto = new BrandReadDto();
        for (Brand b : brandList) {
            dto = brandMapper.brandToBrandReadDto(b);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
