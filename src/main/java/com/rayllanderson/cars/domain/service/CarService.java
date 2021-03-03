package com.rayllanderson.cars.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.repositories.CarRepository;

@Service
public class CarService {
    
    @Autowired
    private CarRepository repository;
    
    public List<Car> findAll() {
	return repository.findAll();
    }

}
