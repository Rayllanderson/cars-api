package com.rayllanderson.cars.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.service.CarService;
import com.rayllanderson.cars.domain.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1.0/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
	return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Long id) {
	try {
	    return ResponseEntity.ok(service.findById(id));
	} catch (ObjectNotFoundException e) {
	    return ResponseEntity.notFound().build();
	}
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Car>> getByType(@PathVariable CarType type) {
	return ResponseEntity.ok(service.findByType(type));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Car car) {
	car = service.save(car);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }

}
