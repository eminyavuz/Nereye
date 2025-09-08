package com.emin.nereye.domain.car.api;
import com.emin.nereye.domain.car.impl.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

 Car findById(Integer theId);

    void deleteById(Integer theId);

    Car save(Car car);

    void update(CarDto dto);

  CarDto getCar(int id);

    List<CarDto> getAll(List<Car> cars);

}
