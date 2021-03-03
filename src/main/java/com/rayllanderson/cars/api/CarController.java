package com.rayllanderson.cars.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.service.CarService;
import com.rayllanderson.cars.domain.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1.0/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public List<Car> getAll() {
	return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Long id) {
	try {
	    return ResponseEntity.ok(service.findById(id));
	} catch (ObjectNotFoundException e) {
	    return ResponseEntity.notFound().build();
	}
    }

}
