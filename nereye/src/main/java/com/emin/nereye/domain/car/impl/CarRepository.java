package com.emin.nereye.domain.car.impl;


import com.emin.nereye.domain.car.impl.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
