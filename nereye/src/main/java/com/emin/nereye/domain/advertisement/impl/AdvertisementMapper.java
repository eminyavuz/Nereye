package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import com.emin.nereye.domain.advertisement.impl.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {
    @Mapping(source = "daily_price", target = "daily_price")
    @Mapping(source = "deposit", target = "deposit")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "car", target = "car")
    AdvertisementDto toAdDto(Advertisement ad);

    @Mapping(source = "daily_price", target = "daily_price")
    @Mapping(source = "deposit", target = "deposit")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "car", target = "car")
    Advertisement toAd(AdvertisementDto dto);




}
