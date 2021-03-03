package com.rayllanderson.cars.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayllanderson.cars.domain.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
