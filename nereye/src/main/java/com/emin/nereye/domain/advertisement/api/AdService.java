package com.emin.nereye.domain.advertisement.api;

import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementCreateDto;
import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementReadDto;
import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementUpdateDto;
import com.emin.nereye.domain.advertisement.impl.Advertisement;

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
