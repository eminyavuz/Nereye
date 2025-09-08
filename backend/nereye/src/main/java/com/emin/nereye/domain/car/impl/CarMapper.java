package com.emin.nereye.domain.car.impl;

import com.emin.nereye.domain.car.api.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "car_id", target = "id")
    @Mapping(source = "fuel_type", target = "fuel_type")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "gear_type", target = "gear_type")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "img_url", target = "img_url")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    Car toCar(CarDto dto);

    @Mapping(source = "id", target = "car_id")
    @Mapping(source = "fuel_type", target = "fuel_type")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "gear_type", target = "gear_type")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "img_url", target = "img_url")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    CarDto toCarDto(Car car);






}
