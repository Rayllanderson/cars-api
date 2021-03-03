package com.rayllanderson.cars.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByType(CarType type);

}
