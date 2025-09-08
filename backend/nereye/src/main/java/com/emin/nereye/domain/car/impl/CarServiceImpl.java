package com.emin.nereye.domain.car.impl;
import com.emin.nereye.domain.car.api.CarDto;
import com.emin.nereye.domain.car.api.CarService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarMapper carMapper;
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<Car> findAll() {
        List<Car> carList = carRepository.findAll();
        return carList;
    }

    @Override
    public  Car findById(Integer theId) {
        Optional<Car> result = carRepository.findById(theId);
        Car car = null;
        if (result.isPresent()) {
            car = result.get();
        } else throw new EntityNotFoundException("Araba Bulunamadı ");
        return car;
    }

    @Override
    @Transactional
    public void deleteById(Integer theId) {
        Car car = findById(theId);
        carRepository.delete(car);

    }

    @Override
    @Transactional
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void update( CarDto dto) {

        carRepository.save(carMapper.toCar(dto));

    }

    public CarDto getCar(int id) {
        return carMapper.toCarDto(findById(id));
    }


    @Override
    public List<CarDto> getAll(List<Car> cars) {
        List<CarDto> dtoList = new ArrayList<>();
        CarDto dto = new CarDto();
        for (Car c : cars) {
            dto = carMapper.toCarDto(c);
            dtoList.add(dto);
        }
        return dtoList;
    }
}


