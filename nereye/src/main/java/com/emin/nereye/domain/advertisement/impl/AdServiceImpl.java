package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementCreateDto;
import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementReadDto;
import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementUpdateDto;
import com.emin.nereye.mapper.AdvertisementMapper;
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
    public AdvertisementCreateDto save(Advertisement ad) {
        adRepository.save(ad);
        return advertisementMapper.adToAdCreateDto(ad);
    }

    @Override
    public AdvertisementUpdateDto update(int theId, AdvertisementUpdateDto dto) {
        Advertisement ad = findById(theId);
        ad = advertisementMapper.adUpdateDtoToAd(dto);
        adRepository.save(ad);
        return advertisementMapper.adToAdUpdateDto(ad);

    }

    @Override
    public AdvertisementReadDto getAd(Advertisement ad) {

        return advertisementMapper.adToAdReadDto(ad);
    }

    @Override
    public List<AdvertisementReadDto> getAll(List<Advertisement> adList) {
        List<AdvertisementReadDto> dtoList = new ArrayList<>();
        AdvertisementReadDto dto = null;
        for (Advertisement ad : adList) {
            dto = advertisementMapper.adToAdReadDto(ad);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
