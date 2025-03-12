package com.emin.nereye.controller;

import com.emin.nereye.Service.BrandService;
import com.emin.nereye.Service.CarService;
import com.emin.nereye.Service.ColorService;
import com.emin.nereye.dto.CarDto;
import com.emin.nereye.entity.Car;
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
    public List<CarDto> carList(){
        List<CarDto> cars= carService.getAll(carService.findAll());
return cars;
}
@PutMapping("/update-car/{id}")
public Car updateCar(@PathVariable Integer id, CarDto car){
        Car theCar= carService.findById(id);
        CarDto dto= new CarDto();
     theCar=dto.dtoToCar(theCar,car);
       theCar=carService.save(theCar);
       return theCar;

}
@PostMapping("/save")
    public Car save(@RequestBody Car car){
 Car dbCar= carService.save(car);
 return dbCar;
}

}
