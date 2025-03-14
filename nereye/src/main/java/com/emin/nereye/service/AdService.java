package com.emin.nereye.Service;

import com.emin.nereye.dto.AdvertisementDto.AdvertisementCreateDto;
import com.emin.nereye.dto.AdvertisementDto.AdvertisementReadDto;
import com.emin.nereye.dto.AdvertisementDto.AdvertisementUpdateDto;
import  com.emin.nereye.entity.Advertisement;

import java.util.List;

public interface AdService {
    List<Advertisement> findAll();
    Advertisement findById(int theId);
    void deleteById(int theId);
    AdvertisementCreateDto save(Advertisement ad);
    AdvertisementUpdateDto update(int theId, AdvertisementUpdateDto dto);
    AdvertisementReadDto getAd(Advertisement ad);
    List<AdvertisementReadDto> getAll(List<Advertisement> adList);
}
