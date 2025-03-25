package com.emin.nereye.domain.car.api;
import com.emin.nereye.domain.car.impl.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findById(Integer theId);

    void deleteById(Integer theId);

    Car save(Car car);

    void update(Integer theId, CarDto dto);

    CarDto getCar(Car car);

    List<CarDto> getAll(List<Car> cars);

}
