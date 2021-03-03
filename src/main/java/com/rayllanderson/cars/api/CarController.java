package com.rayllanderson.cars.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.service.CarService;

@RestController
@RequestMapping("/api/v1.0/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public List<Car> getAll() {
	return service.findAll();
    }

}
