package com.emin.nereye.domain.car.web;

import com.emin.nereye.domain.car.api.carDto.CarReadDto;
import com.emin.nereye.domain.car.api.carDto.CarUpdateDto;
import com.emin.nereye.domain.car.impl.Car;
import com.emin.nereye.domain.brand.api.BrandService;
import com.emin.nereye.domain.car.api.CarService;
import com.emin.nereye.domain.color.api.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private CarService carService;
    private BrandService brandService;
    private ColorService colorService;

    @Autowired
    public CarController(CarService carService,
                         BrandService brandService,
                         ColorService colorService) {
        this.brandService = brandService;
        this.carService = carService;
        this.colorService = colorService;
    }

    @GetMapping("/carlist")
    public List<CarReadDto> carList() {
        List<CarReadDto> cars = carService.getAll(carService.findAll());
        return cars;
    }

    @PutMapping("/update-car/{id}")
    public void updateCar(@PathVariable Integer id, CarUpdateDto car) {
        carService.update(id, car);


    }

    @PostMapping("/save")
    public Car save(@RequestBody Car car) {
        Car dbCar = carService.save(car);
        return dbCar;
    }

}
