package com.emin.nereye.mapper;

import com.emin.nereye.domain.car.api.carDto.CarCreateDto;
import com.emin.nereye.domain.car.api.carDto.CarReadDto;
import com.emin.nereye.domain.car.api.carDto.CarUpdateDto;
import com.emin.nereye.domain.car.impl.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "fuel_type", target = "fuel_type")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "gear_type", target = "gear_type")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    Car carCreateDtoToCar(CarCreateDto dto);

    @Mapping(source = "fuel_type", target = "fuel_type")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "gear_type", target = "gear_type")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    CarCreateDto carToCarCreateDto(Car car);

    @Mapping(source = "fuel_type", target = "fuel_type")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "gear_type", target = "gear_type")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    Car carReadeDtoToCar(CarReadDto dto);

    @Mapping(source = "fuel_type", target = "fuel_type")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "gear_type", target = "gear_type")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    CarReadDto carToCarReadDto(Car car);

    @Mapping(source = "km", target = "km")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "color", target = "color")
    Car carUpdateDtoToCar(CarUpdateDto dto);

    @Mapping(source = "color", target = "color")
    @Mapping(source = "km", target = "km")
    @Mapping(source = "capacity", target = "capacity")
    CarUpdateDto carToCarUpdateDto(CarUpdateDto dto);


}
