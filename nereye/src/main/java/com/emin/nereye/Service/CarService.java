package com.emin.nereye.Service;

import com.emin.nereye.dto.CarDto.CarReadDto;
import com.emin.nereye.dto.CarDto.CarUpdateDto;
import com.emin.nereye.entity.Car;

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
