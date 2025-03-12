package com.emin.nereye.Service;

import com.emin.nereye.dto.BrandDto;
import com.emin.nereye.entity.Brand;
import com.emin.nereye.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService
{
    private BrandRepository brandRepository;
    public BrandServiceImpl(BrandRepository brandRepository){
        this.brandRepository=brandRepository;

    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList= brandRepository.findAll();

        return brandList;
    }

    @Override
    public Brand findById(int theId) {
        Optional<Brand> result= brandRepository.findById(theId);
        Brand b=null;
        if(result.isPresent()){
            b= result.get();
        }
        else {
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
    public void update(int theId,BrandDto dto) {
      Brand brand= findById(theId);
      brand=dto.dtoToBrand(brand,dto);
        brandRepository.save(brand);

    }

    @Override
    public BrandDto get(Brand brand) {
        BrandDto dto= new BrandDto();
        dto.BrandToBrandDto(brand);

        return dto;
    }

    @Override
    public List<BrandDto> getAll(List<Brand> brandList) {
        List<BrandDto> dtoList= new ArrayList<>();
        BrandDto dto= new BrandDto();
        for( Brand b: brandList){
            dto.BrandToBrandDto(b);
            dtoList.add(dto);
        }
        return  dtoList;
    }
}
