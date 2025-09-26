package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.api.AdService;
import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import com.emin.nereye.domain.car.api.CarService;
import com.emin.nereye.domain.car.impl.Car;
import com.emin.nereye.domain.car.impl.CarMapper;
import com.emin.nereye.domain.user.impl.User;
import com.emin.nereye.domain.user.impl.UserRepository;
import com.emin.nereye.security.JWTService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    private final AdvertisementMapper advertisementMapper;
    private final JWTService jwtService;
    private AdRepository adRepository;
    private CarService carService;
    private CarMapper carMapper;
    private UserRepository userRepository;

    @Autowired
    public AdServiceImpl(CarMapper carMapper
            , AdRepository adRepository
            , AdvertisementMapper advertisementMapper
            , UserRepository userRepository
            , CarService carService
            , JWTService jwtService
    ) {
        this.adRepository = adRepository;
        this.advertisementMapper = advertisementMapper;
        this.userRepository = userRepository;
        this.carService = carService;
        this.carMapper = carMapper;
        this.jwtService = jwtService;
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
    public AdvertisementDto save(AdvertisementDto ad, int userId) {
        Advertisement advert = advertisementMapper.toAd(ad);

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        advert.setOwner(owner);

      //  Car savedCar = carMapper.toCar(carService.save(ad.getCar()));
        Car savedCar= carMapper.toCar(ad.getCar());
        advert.setCar(savedCar);
        Advertisement saved = adRepository.save(advert);

        return advertisementMapper.toAdDto(saved);
    }

    @Override
    public AdvertisementDto update(AdvertisementDto dto) {
        Advertisement ad = null;
        try {
            ad = findById(dto.getAd_id());
        } catch (Exception ex) {
            throw new EntityNotFoundException("İlan bulunamadı");
        }
        adRepository.save(ad);
        return advertisementMapper.toAdDto(ad);

    }

    @Override
    public AdvertisementDto getAd(int id) {

        return advertisementMapper.toAdDto(findById(id));
    }

    @Override
    public List<AdvertisementDto> getMyAds(String username) {
        User user = userRepository.findByUsername(username);
        List<Advertisement> tmp = adRepository.findByOwner_Id(user.getId());

        List<AdvertisementDto> myAds = new ArrayList<>();
        for (Advertisement ad : tmp) {
            myAds.add(advertisementMapper.toAdDto(ad));
        }
        return myAds;
    }

    @Override
    public List<AdvertisementDto> getMyRentedAds(String username) {
        User user = userRepository.findByUsername(username);
        List<Advertisement> tmp = adRepository.findByTenet_Id(user.getId());

        List<AdvertisementDto> myAds = new ArrayList<>();
        for (Advertisement ad : tmp) {
            myAds.add(advertisementMapper.toAdDto(ad));
        }
        return myAds;
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

    @Override
    @Transactional
    public AdvertisementDto rent(AdvertisementDto dto, Integer tenantId) {
        if (!Objects.equals(dto.getOwner_id(), tenantId)) {
            dto.setTenet_id(tenantId);
            return update(dto);
        } else if (tenantId == null) {
            throw new RuntimeException("Kiracının Id'si alınamadı");
        } else {
            throw new RuntimeException("Kendi ilanınızı kiralayamazsınız");
        }

    }
}
