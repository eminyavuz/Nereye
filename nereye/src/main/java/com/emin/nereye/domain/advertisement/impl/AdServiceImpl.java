package com.emin.nereye.domain.advertisement.impl;
import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import com.emin.nereye.domain.advertisement.api.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    private final AdvertisementMapper advertisementMapper;
    private AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository, AdvertisementMapper advertisementMapper) {
        this.adRepository = adRepository;
        this.advertisementMapper = advertisementMapper;
    }

    @Override
    public List<Advertisement> findAll() {
        List<Advertisement> ads = adRepository.findAll();
        return ads;
    }

    @Override
    public Advertisement findById(int theId) {
        Optional<Advertisement> result = adRepository.findById(theId);
        Advertisement ad = null;
        if (result.isPresent()) {
            ad = result.get();
        } else {
            throw new RuntimeException("User not found");
        }

        return ad;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        adRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public AdvertisementDto save(AdvertisementDto ad) {
        adRepository.save(advertisementMapper.toAd(ad));
        return ad;
    }

    @Override
    public AdvertisementDto update(int theId, AdvertisementDto dto) {
        Advertisement ad = findById(theId);
        ad = advertisementMapper.toAd(dto);
        adRepository.save(ad);
        return advertisementMapper.toAdDto(ad);

    }

    @Override
    public AdvertisementDto getAd(int id) {

        return advertisementMapper.toAdDto(findById(id));
    }

    @Override
    public List<AdvertisementDto> getAll(List<Advertisement> adList) {
        List<AdvertisementDto> dtoList = new ArrayList<>();
        AdvertisementDto dto = null;
        for (Advertisement ad : adList) {
            dto = advertisementMapper.toAdDto(ad);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
