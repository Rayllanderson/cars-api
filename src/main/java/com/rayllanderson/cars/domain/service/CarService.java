package com.rayllanderson.cars.domain.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rayllanderson.cars.domain.entities.Car;

@Service
public class CarService {
    
    public List<Car> findAll() {
	return Arrays.asList(new Car(1L, "Corsa"), new Car(2L, "Argo"), new Car(3L, "Uno"));
    }

}
