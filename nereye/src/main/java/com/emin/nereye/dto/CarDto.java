package com.emin.nereye.dto;

import com.emin.nereye.entity.Brand;
import com.emin.nereye.entity.Car;
import com.emin.nereye.entity.Color;
import com.emin.nereye.enumeration.fuel_type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private fuel_type fuel_type;
    private int km;
    private boolean gear_type;
    private int capacity;
    private String Model;
    private int year;
    private Brand brand;
    private Color color;

    public CarDto carToCarDto(Car car){
        CarDto dto= new CarDto();
        dto.setKm(car.getKm());
        dto.setGear_type(car.getGear_type());
        dto.setCapacity(car.getCapacity());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setBrand(car.getBrand());
        dto.setColor(car.getColor());
        dto.setFuel_type(car.getFuel_type());
      return dto;

    }
    public Car dtoToCar(Car car, CarDto dto){
        car.setKm(dto.getKm());
        car.setCapacity(dto.getCapacity());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setBrand(dto.getBrand());
        car.setColor(dto.getColor());
        car.setFuel_type(dto.getFuel_type());
        car.setGear_type(dto.gear_type);
        return car;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "fuel_type=" + fuel_type +
                ", km=" + km +
                ", gear_type=" + gear_type +
                ", capacity=" + capacity +
                ", Model='" + Model + '\'' +
                ", year=" + year +
                ", brand=" + brand.getBrand_name() +
                ", color=" + color.getColor_name() +
                '}';
    }
}
