package com.emin.nereye.Service;

import com.emin.nereye.dto.CarDto;
import com.emin.nereye.entity.Car;
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
    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
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
    public void save(Car car) {
carRepository.save(car);
    }

    @Override
    public void update(Integer theId,CarDto dto) {
        carRepository.save(dto.dtoToCar(findById(theId),dto));
    }

    @Override
    public CarDto getCar(Car car) {
        CarDto dto=new CarDto();
        return dto.carToCarDto(car) ;
    }

    @Override
    public List<CarDto> getAll(List<Car> cars) {
        List<CarDto> dtoList= new ArrayList<>();
        CarDto dto= new CarDto();
        for( Car c: cars){
            dto.carToCarDto(c);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
