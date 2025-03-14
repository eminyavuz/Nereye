package com.emin.nereye.Service;

import com.emin.nereye.dto.CarDto.CarReadDto;
import com.emin.nereye.dto.CarDto.CarUpdateDto;
import com.emin.nereye.entity.Car;
import com.emin.nereye.mapper.CarMapper;
import com.emin.nereye.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private  final CarMapper carMapper;
    @Autowired
    public CarServiceImpl(CarRepository carRepository,CarMapper carMapper){
        this.carRepository=carRepository;
        this.carMapper=carMapper;
    }

    @Override
    public List<Car> findAll() {
        List <Car> carList= carRepository.findAll();
        return carList;
    }

    @Override
    public Car findById(Integer theId) {
        Optional <Car> result= carRepository.findById(theId);
        Car car= null;
        if(result.isPresent()){
            car= result.get();
        }
        else throw new RuntimeException("Car did not found");
        return car;
    }

    @Override
@Transactional
    public void deleteById(Integer theId) {
      Car car= findById(theId);
        carRepository.delete(car);

    }

    @Override
    @Transactional
    public Car save(Car car) {
return carRepository.save(car);
    }

    @Override
    public void update(Integer theId, CarUpdateDto dto) {

        carRepository.save(carMapper.carUpdateDtoToCar(dto));

    }

    @Override
    public CarReadDto getCar(Car car) {

        return carMapper.carToCarReadDto(car) ;
    }

    @Override
    public List<CarReadDto> getAll(List<Car> cars) {
        List<CarReadDto> dtoList= new ArrayList<>();
        CarReadDto dto= new CarReadDto();
        for( Car c: cars){
            dto=carMapper.carToCarReadDto(c);
            dtoList.add(dto);
        }
        return dtoList;
    }
}


