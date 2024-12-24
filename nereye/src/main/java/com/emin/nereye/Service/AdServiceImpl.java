package com.emin.nereye.Service;

import com.emin.nereye.dto.AdvertisementDto;
import com.emin.nereye.entity.Advertisement;
import com.emin.nereye.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

private AdRepository adRepository;
@Autowired
public AdServiceImpl(AdRepository adRepository){
    this.adRepository=adRepository;
}
    @Override
    public List<Advertisement> findAll() {
      List<Advertisement> ads= adRepository.findAll();
              return ads;
    }

    @Override
    public Advertisement findById(int theId) {
        Optional<Advertisement> result = adRepository.findById(theId);
        Advertisement ad= null;
        if( result.isPresent()){
          ad=result.get();
        }
        else{
            throw  new RuntimeException("User not found");
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
    public void save(Advertisement ad) {
adRepository.save(ad);
    }

    @Override
    public void update(int theId,AdvertisementDto dto) {
    Advertisement ad= findById(theId);
    ad=dto.dtoToAd(ad,dto);
    adRepository.save(ad);

    }

    @Override
    public AdvertisementDto getAd(Advertisement ad) {
    AdvertisementDto dto= new AdvertisementDto();
    dto.adtoAdDto(ad);
        return dto;
    }

    @Override
    public List<AdvertisementDto> getAll(List<Advertisement> adList) {
    List<AdvertisementDto> dtoList= new ArrayList<>();
    AdvertisementDto dto= null;
    for (Advertisement ad : adList){
        dto.adtoAdDto(ad);
        dtoList.add(dto);
    }
    return dtoList;
    }
}
