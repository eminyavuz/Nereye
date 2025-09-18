package com.emin.nereye.domain.advertisement.api;


import com.emin.nereye.domain.advertisement.impl.Advertisement;

import java.util.List;

public interface AdService {
    List<Advertisement> findAll();

    Advertisement findById(int theId);

    void deleteById(int theId);

    AdvertisementDto save(AdvertisementDto ad, int userId);

    AdvertisementDto update(AdvertisementDto dto);

    AdvertisementDto getAd(int id);

    public List<AdvertisementDto> getMyAds(String Token);

    public List<AdvertisementDto> getMyRentedAds(String Token);

    List<AdvertisementDto> getAll(List<Advertisement> adList);

    AdvertisementDto rent(AdvertisementDto dto, Integer tenantId);
}
