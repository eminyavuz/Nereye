package com.emin.nereye.domain.car.web;

import com.emin.nereye.domain.brand.api.BrandService;
import com.emin.nereye.domain.car.api.CarDto;
import com.emin.nereye.domain.car.api.CarService;
import com.emin.nereye.domain.car.impl.Car;
import com.emin.nereye.domain.color.api.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private CarService carService;
    private final BrandService brandService;
    private final ColorService colorService;

    @Autowired
    public CarController(CarService carService,
                         BrandService brandService,
                         ColorService colorService) {
        this.brandService = brandService;
        this.carService = carService;
        this.colorService = colorService;
    }

    @GetMapping("/get/{id}")
    public CarDto getCar(@PathVariable int id){

        return carService.getCar(id);
    }
    @GetMapping("/getAll")
    public List<CarDto> carList() {
        List<CarDto> cars = carService.getAll(carService.findAll());
        return cars;
    }

    @PutMapping("/update")
    public void updateCar( CarDto car) {
        carService.update( car);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        carService.deleteById(id);
    }

    @PostMapping("/save")
    public Car save(@RequestBody Car car) {
        Car dbCar = carService.save(car);
        return dbCar;
    }

}
