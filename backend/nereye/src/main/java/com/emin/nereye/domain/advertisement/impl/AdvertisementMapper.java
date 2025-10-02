package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import com.emin.nereye.domain.car.impl.CarMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface AdvertisementMapper {
    @Mapping(source = "ad_id", target = "ad_id")
    @Mapping(source = "daily_price", target = "daily_price")
    @Mapping(source = "deposit", target = "deposit")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "car", target = "car")
    @Mapping(source = "owner.id", target = "owner_id")
    @Mapping(source = "tenet.id", target = "tenet_id")
    AdvertisementDto toAdDto(Advertisement ad);

    @Mapping(source = "ad_id", target = "ad_id")
    @Mapping(source = "daily_price", target = "daily_price")
    @Mapping(source = "deposit", target = "deposit")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "car", target = "car")
   
    Advertisement toAd(AdvertisementDto dto);
}
