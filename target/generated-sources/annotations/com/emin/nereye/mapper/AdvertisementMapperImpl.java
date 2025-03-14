package com.emin.nereye.mapper;

import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementCreateDto;
import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementReadDto;
import com.emin.nereye.domain.advertisement.api.advertisementDto.AdvertisementUpdateDto;
import com.emin.nereye.domain.advertisement.impl.Advertisement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-14T20:46:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AdvertisementMapperImpl implements AdvertisementMapper {

    @Override
    public AdvertisementCreateDto adToAdCreateDto(Advertisement ad) {
        if ( ad == null ) {
            return null;
        }

        AdvertisementCreateDto advertisementCreateDto = new AdvertisementCreateDto();

        advertisementCreateDto.setDaily_price( String.valueOf( ad.getDaily_price() ) );
        advertisementCreateDto.setDeposit( String.valueOf( ad.getDeposit() ) );
        advertisementCreateDto.setLocation( ad.getLocation() );
        advertisementCreateDto.setCar( ad.getCar() );

        return advertisementCreateDto;
    }

    @Override
    public Advertisement adCreateDtoToAd(AdvertisementCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Advertisement advertisement = new Advertisement();

        if ( dto.getDaily_price() != null ) {
            advertisement.setDaily_price( Integer.parseInt( dto.getDaily_price() ) );
        }
        if ( dto.getDeposit() != null ) {
            advertisement.setDeposit( Integer.parseInt( dto.getDeposit() ) );
        }
        advertisement.setLocation( dto.getLocation() );
        advertisement.setCar( dto.getCar() );

        return advertisement;
    }

    @Override
    public AdvertisementUpdateDto adToAdUpdateDto(Advertisement ad) {
        if ( ad == null ) {
            return null;
        }

        AdvertisementUpdateDto advertisementUpdateDto = new AdvertisementUpdateDto();

        advertisementUpdateDto.setDaily_price( String.valueOf( ad.getDaily_price() ) );
        advertisementUpdateDto.setDeposit( String.valueOf( ad.getDeposit() ) );
        advertisementUpdateDto.setLocation( ad.getLocation() );

        return advertisementUpdateDto;
    }

    @Override
    public Advertisement adUpdateDtoToAd(AdvertisementUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Advertisement advertisement = new Advertisement();

        if ( dto.getDaily_price() != null ) {
            advertisement.setDaily_price( Integer.parseInt( dto.getDaily_price() ) );
        }
        if ( dto.getDeposit() != null ) {
            advertisement.setDeposit( Integer.parseInt( dto.getDeposit() ) );
        }
        advertisement.setLocation( dto.getLocation() );

        return advertisement;
    }

    @Override
    public AdvertisementReadDto adToAdReadDto(Advertisement ad) {
        if ( ad == null ) {
            return null;
        }

        AdvertisementReadDto advertisementReadDto = new AdvertisementReadDto();

        advertisementReadDto.setDaily_price( String.valueOf( ad.getDaily_price() ) );
        advertisementReadDto.setDeposit( String.valueOf( ad.getDeposit() ) );
        advertisementReadDto.setLocation( ad.getLocation() );
        advertisementReadDto.setCar( ad.getCar() );

        return advertisementReadDto;
    }

    @Override
    public Advertisement adReadDtoToAd(AdvertisementReadDto dto) {
        if ( dto == null ) {
            return null;
        }

        Advertisement advertisement = new Advertisement();

        if ( dto.getDaily_price() != null ) {
            advertisement.setDaily_price( Integer.parseInt( dto.getDaily_price() ) );
        }
        if ( dto.getDeposit() != null ) {
            advertisement.setDeposit( Integer.parseInt( dto.getDeposit() ) );
        }
        advertisement.setLocation( dto.getLocation() );
        advertisement.setCar( dto.getCar() );

        return advertisement;
    }
}
