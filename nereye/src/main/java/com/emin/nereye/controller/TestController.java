package com.emin.nereye.controller;

import com.emin.nereye.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class TestController {
    private final CarService carService;

    @Autowired
    public TestController(CarService carService) {
        this.carService = carService;
    }

    // Car silme endpoint'i
    @DeleteMapping
            ("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable("id") int id) {
        carService.deleteById(id);
        return new ResponseEntity<>("Car with ID " + id + " has been deleted.", HttpStatus.OK);
    }
}

