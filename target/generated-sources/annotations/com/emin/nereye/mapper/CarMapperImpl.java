package com.emin.nereye.mapper;

import com.emin.nereye.domain.car.api.carDto.CarCreateDto;
import com.emin.nereye.domain.car.api.carDto.CarReadDto;
import com.emin.nereye.domain.car.api.carDto.CarUpdateDto;
import com.emin.nereye.domain.car.impl.Car;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-14T20:46:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car carCreateDtoToCar(CarCreateDto dto) {
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

        return car;
    }

    @Override
    public CarCreateDto carToCarCreateDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarCreateDto carCreateDto = new CarCreateDto();

        carCreateDto.setFuel_type( car.getFuel_type() );
        carCreateDto.setKm( car.getKm() );
        carCreateDto.setGear_type( car.getGear_type() );
        carCreateDto.setCapacity( car.getCapacity() );
        carCreateDto.setModel( car.getModel() );
        carCreateDto.setYear( car.getYear() );
        carCreateDto.setBrand( car.getBrand() );
        carCreateDto.setColor( car.getColor() );

        return carCreateDto;
    }

    @Override
    public Car carReadeDtoToCar(CarReadDto dto) {
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

        return car;
    }

    @Override
    public CarReadDto carToCarReadDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarReadDto carReadDto = new CarReadDto();

        carReadDto.setFuel_type( car.getFuel_type() );
        carReadDto.setKm( car.getKm() );
        carReadDto.setGear_type( car.getGear_type() );
        carReadDto.setCapacity( car.getCapacity() );
        carReadDto.setModel( car.getModel() );
        carReadDto.setYear( car.getYear() );
        carReadDto.setBrand( car.getBrand() );
        carReadDto.setColor( car.getColor() );

        return carReadDto;
    }

    @Override
    public Car carUpdateDtoToCar(CarUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Car car = new Car();

        car.setKm( dto.getKm() );
        car.setCapacity( dto.getCapacity() );
        car.setColor( dto.getColor() );

        return car;
    }

    @Override
    public CarUpdateDto carToCarUpdateDto(CarUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        CarUpdateDto carUpdateDto = new CarUpdateDto();

        carUpdateDto.setColor( dto.getColor() );
        carUpdateDto.setKm( dto.getKm() );
        carUpdateDto.setCapacity( dto.getCapacity() );

        return carUpdateDto;
    }
}
