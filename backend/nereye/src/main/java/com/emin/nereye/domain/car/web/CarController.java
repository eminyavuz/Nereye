package com.emin.nereye.domain.car.web;

import com.emin.nereye.domain.brand.api.BrandService;
import com.emin.nereye.domain.car.api.CarDto;
import com.emin.nereye.domain.car.api.CarService;
import com.emin.nereye.domain.car.impl.Car;
import com.emin.nereye.domain.color.api.ColorService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;
    private BrandService brandService;
    private ColorService colorService;

    @Autowired
    public CarController(CarService carService,
                         BrandService brandService,
                         ColorService colorService
                         ) {
        this.brandService = brandService;
        this.carService = carService;
        this.colorService = colorService;
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable int id){

        return carService.getCar(id);
    }
    @GetMapping("/carlist")
    public List<CarDto> carList() {
        List<CarDto> cars = carService.getAll(carService.findAll());
        return cars;
    }

    @PutMapping("/update-car/{id}")
    public void updateCar(@PathVariable Integer id, CarDto car) {
        carService.update(id, car);

    }

    @PostMapping("/save")
    public Car save(@RequestBody Car car) {
        Car dbCar = carService.save(car);
        return dbCar;
    }

}
