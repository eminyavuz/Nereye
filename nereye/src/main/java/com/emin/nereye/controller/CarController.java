package com.emin.nereye.controller;

import com.emin.nereye.Service.BrandService;
import com.emin.nereye.Service.CarService;
import com.emin.nereye.Service.ColorService;
import com.emin.nereye.dto.CarDto.CarReadDto;
import com.emin.nereye.dto.CarDto.CarUpdateDto;
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
    public List<CarReadDto> carList(){
        List<CarReadDto> cars= carService.getAll(carService.findAll());
return cars;
}
@PutMapping("/update-car/{id}")
public void updateCar(@PathVariable Integer id, CarUpdateDto car){
        carService.update(id,car);


}
@PostMapping("/save")
    public Car save(@RequestBody Car car){
 Car dbCar= carService.save(car);
 return dbCar;
}

}
