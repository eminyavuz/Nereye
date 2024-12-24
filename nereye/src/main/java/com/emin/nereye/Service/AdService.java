package com.emin.nereye.Service;

import  com.emin.nereye.dto.AdvertisementDto;
import  com.emin.nereye.entity.Advertisement;
import  com.emin.nereye.entity.Brand;

import java.util.List;

public interface AdService {
    List<Advertisement> findAll();
    Advertisement findById(int theId);
    void deleteById(int theId);
    void save(Advertisement ad);
    void update(int theId,AdvertisementDto dto);
    AdvertisementDto getAd(Advertisement ad);
    List<AdvertisementDto> getAll(List<Advertisement> adList);
}
