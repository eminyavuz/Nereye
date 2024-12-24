package com.emin.nereye.Service;

import ch.qos.logback.core.model.INamedModel;
import com.emin.nereye.dto.CarDto;
import com.emin.nereye.entity.Brand;
import com.emin.nereye.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car findById(Integer theId);
    void deleteById(Integer theId);
    void save(Car car);
    void update(Integer theId,CarDto dto);
    CarDto getCar(Car car);
    List<CarDto> getAll(List<Car> cars);

}
