package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T20:39:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AdvertisementMapperImpl implements AdvertisementMapper {

    @Override
    public AdvertisementDto toAdDto(Advertisement ad) {
        if ( ad == null ) {
            return null;
        }

        AdvertisementDto advertisementDto = new AdvertisementDto();

        advertisementDto.setDaily_price( ad.getDaily_price() );
        advertisementDto.setDeposit( ad.getDeposit() );
        advertisementDto.setLocation( ad.getLocation() );
        advertisementDto.setCar( ad.getCar() );
        advertisementDto.setAd_id( ad.getAd_id() );

        return advertisementDto;
    }

    @Override
    public Advertisement toAd(AdvertisementDto dto) {
        if ( dto == null ) {
            return null;
        }

        Advertisement advertisement = new Advertisement();

        advertisement.setDaily_price( dto.getDaily_price() );
        advertisement.setDeposit( dto.getDeposit() );
        advertisement.setLocation( dto.getLocation() );
        advertisement.setCar( dto.getCar() );
        advertisement.setAd_id( dto.getAd_id() );

        return advertisement;
    }
}
