package com.rayllanderson.cars.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.repositories.CarRepository;
import com.rayllanderson.cars.domain.service.exceptions.ObjectNotFoundException;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public List<Car> findAll() {
	return repository.findAll();
    }

    public Car findById(Long id) {
	return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found on the database"));
    }
    
    public List<Car> findByType(CarType type){
	return repository.findByType(type);
    }
    
    public Car save (Car car) {
	return repository.save(car);
    }

}
