package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {
    @Mapping(source = "ad_id", target = "ad_id")
    @Mapping(source = "daily_price", target = "daily_price")
    @Mapping(source = "deposit", target = "deposit")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "car", target = "car")
    @Mapping(source = "owner", target = "owner_id")
    @Mapping(source = "tenet", target = "tenet_id")
    AdvertisementDto toAdDto(Advertisement ad);

    @Mapping(source = "ad_id", target = "ad_id")
    @Mapping(source = "daily_price", target = "daily_price")
    @Mapping(source = "deposit", target = "deposit")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "car", target = "car")
    @Mapping(source = "owner_id", target = "owner")
    @Mapping(source = "tenet_id", target = "tenet")
    Advertisement toAd(AdvertisementDto dto);


}
