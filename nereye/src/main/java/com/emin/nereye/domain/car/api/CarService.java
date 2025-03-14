package com.emin.nereye.domain.car.api;

import com.emin.nereye.domain.car.api.carDto.CarReadDto;
import com.emin.nereye.domain.car.api.carDto.CarUpdateDto;
import com.emin.nereye.domain.car.impl.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findById(Integer theId);

    void deleteById(Integer theId);

    Car save(Car car);

    void update(Integer theId, CarUpdateDto dto);

    CarReadDto getCar(Car car);

    List<CarReadDto> getAll(List<Car> cars);

}
