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
        System.out.println("getMyAds called with username: " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found for username: " + username);
            return new ArrayList<>();
        }
        System.out.println("Found user: " + user.getId());
        List<Advertisement> tmp = adRepository.findByOwner_Id(user.getId());
        System.out.println("Found " + tmp.size() + " ads for user");

        List<AdvertisementDto> myAds = new ArrayList<>();
        for (Advertisement ad : tmp) {
            myAds.add(advertisementMapper.toAdDto(ad));
        }
        return myAds;
    }

    @Override
    public List<AdvertisementDto> getMyRentedAds(String username) {
        System.out.println("getMyRentedAds called with username: " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found for username: " + username);
            return new ArrayList<>();
        }
        System.out.println("Found user: " + user.getId());
        List<Advertisement> tmp = adRepository.findByTenet_Id(user.getId());
        System.out.println("Found " + tmp.size() + " rented ads for user");

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
        if (tenantId == null) {
            throw new RuntimeException("Kiracının Id'si alınamadı");
        }

        // İlanı yükle
        Advertisement advertisement = findById(dto.getAd_id());

        // Kendi ilanını kiralama kontrolü
        if (advertisement.getOwner() != null && Objects.equals(advertisement.getOwner().getId(), tenantId)) {
            throw new RuntimeException("Kendi ilanınızı kiralayamazsınız");
        }

        // Zaten kiralanmış mı?
        if (advertisement.getTenet() != null) {
            throw new RuntimeException("Bu ilan zaten kiralanmış");
        }

        // Kiracıyı ata ve kaydet
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Kiracı kullanıcı bulunamadı"));
        advertisement.setTenet(tenant);
        Advertisement saved = adRepository.save(advertisement);

        return advertisementMapper.toAdDto(saved);
    }

    @Override
    @Transactional
    public AdvertisementDto cancelRent(int adId, String username) {
        try {
            System.out.println("cancelRent called with adId: " + adId + ", username: " + username);
            
            // İlanı yükle
            Advertisement advertisement = findById(adId);
            System.out.println("Found advertisement: " + advertisement.getAd_id());
            
            // İlan sahibi kontrolü
            User owner = userRepository.findByUsername(username);
            if (owner == null) {
                System.out.println("User not found: " + username);
                throw new RuntimeException("Kullanıcı bulunamadı: " + username);
            }
            System.out.println("Found owner: " + owner.getId() + ", ad owner: " + (advertisement.getOwner() != null ? advertisement.getOwner().getId() : "null"));
            
            if (advertisement.getOwner() == null || !Objects.equals(advertisement.getOwner().getId(), owner.getId())) {
                throw new RuntimeException("Bu ilanı iptal etme yetkiniz yok. İlan sahibi: " + (advertisement.getOwner() != null ? advertisement.getOwner().getId() : "null") + ", Sizin ID: " + owner.getId());
            }
            
            // Kiracıyı çıkar
            advertisement.setTenet(null);
            Advertisement saved = adRepository.save(advertisement);
            
            System.out.println("Rental cancelled for ad: " + adId);
            return advertisementMapper.toAdDto(saved);
        } catch (Exception e) {
            System.err.println("Error in cancelRent: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
