package com.emin.nereye.domain.car.impl;

import com.emin.nereye.domain.car.api.CarDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T20:39:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car toCar(CarDto dto) {
        if ( dto == null ) {
            return null;
        }

        Car car = new Car();

        car.setFuel_type( dto.getFuel_type() );
        car.setKm( dto.getKm() );
        car.setGear_type( dto.isGear_type() );
        car.setCapacity( dto.getCapacity() );
        car.setModel( dto.getModel() );
        car.setYear( dto.getYear() );
        car.setBrand( dto.getBrand() );
        car.setColor( dto.getColor() );
        car.setId( dto.getId() );
        car.setImg_url( dto.getImg_url() );

        return car;
    }

    @Override
    public CarDto toCarDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setFuel_type( car.getFuel_type() );
        carDto.setKm( car.getKm() );
        carDto.setGear_type( car.isGear_type() );
        carDto.setCapacity( car.getCapacity() );
        carDto.setModel( car.getModel() );
        carDto.setYear( car.getYear() );
        carDto.setBrand( car.getBrand() );
        carDto.setColor( car.getColor() );
        carDto.setId( car.getId() );
        carDto.setImg_url( car.getImg_url() );

        return carDto;
    }
}
