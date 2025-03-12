package com.emin.nereye.mapper;

import com.emin.nereye.dto.AdvertisementDto.AdvertisementCreateDto;
import com.emin.nereye.dto.AdvertisementDto.AdvertisementReadDto;
import com.emin.nereye.dto.AdvertisementDto.AdvertisementUpdateDto;
import com.emin.nereye.entity.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {
@Mapping(source = "daily_price",target = "daily_price")
@Mapping(source = "deposit",target = "deposit")
@Mapping(source = "location",target = "location")
@Mapping(source = "car",target = "car")
    AdvertisementCreateDto adToAdCreateDto(Advertisement ad);
@Mapping(source = "daily_price",target = "daily_price")
@Mapping(source = "deposit",target = "deposit")
@Mapping(source = "location",target = "location")
@Mapping(source = "car",target = "car")
    Advertisement adCreateDtoToAd(AdvertisementCreateDto dto);

    @Mapping(source = "daily_price",target = "daily_price")
    @Mapping(source = "deposit",target = "deposit")
    @Mapping(source = "location",target = "location")
    AdvertisementUpdateDto adToAdUpdateDto(Advertisement ad);
    @Mapping(source = "daily_price",target = "daily_price")
    @Mapping(source = "deposit",target = "deposit")
    @Mapping(source = "location",target = "location")
    Advertisement adUpdateDtoToAd(AdvertisementUpdateDto dto);

    @Mapping(source = "daily_price",target = "daily_price")
    @Mapping(source = "deposit",target = "deposit")
    @Mapping(source = "location",target = "location")
    @Mapping(source = "car",target = "car")
    AdvertisementReadDto adToAdReadDto(Advertisement ad);
    @Mapping(source = "daily_price",target = "daily_price")
    @Mapping(source = "deposit",target = "deposit")
    @Mapping(source = "location",target = "location")
    @Mapping(source = "car",target = "car")
    Advertisement adReadDtoToAd(AdvertisementReadDto dto);


}
