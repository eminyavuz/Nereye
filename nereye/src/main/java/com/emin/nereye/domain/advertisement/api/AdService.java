package com.emin.nereye.domain.advertisement.api;


import com.emin.nereye.domain.advertisement.impl.Advertisement;

import java.util.List;

public interface AdService {
    List<Advertisement> findAll();

    Advertisement findById(int theId);

    void deleteById(int theId);

    AdvertisementDto save(AdvertisementDto ad);

    AdvertisementDto update(int theId, AdvertisementDto dto);

    AdvertisementDto getAd(int id);

    List<AdvertisementDto> getAll(List<Advertisement> adList);
}
