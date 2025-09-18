package com.emin.nereye.domain.car.api;

import com.emin.nereye.domain.car.impl.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findById(Integer theId);

    void deleteById(Integer theId);

    CarDto save(CarDto car);

    void update(CarDto dto);

    CarDto getCar(int id);

    List<CarDto> getAll(List<Car> cars);

}
