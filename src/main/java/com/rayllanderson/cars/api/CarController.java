package com.rayllanderson.cars.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rayllanderson.cars.domain.dto.CarDTO;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.service.CarService;

@RestController
@RequestMapping("/api/v1.0/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll() {
	return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getById(@PathVariable Long id) {
	return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CarDTO>> getByType(@PathVariable CarType type) {
	return ResponseEntity.ok(service.findByType(type));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CarDTO car) {
	car = service.save(car);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarDTO car) {
	service.update(id, car);
	return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
	service.deleteById(id);
	return ResponseEntity.noContent().build();
    }

}
