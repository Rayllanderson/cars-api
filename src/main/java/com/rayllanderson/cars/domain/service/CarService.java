package com.rayllanderson.cars.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayllanderson.cars.domain.dto.CarDTO;
import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.repositories.CarRepository;
import com.rayllanderson.cars.domain.service.exceptions.ObjectNotFoundException;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public List<CarDTO> findAll() {
	return repository.findAll().stream().map(x -> new CarDTO(x)).collect(Collectors.toList());
    }

    public CarDTO findById(Long id) throws ObjectNotFoundException {
	return new CarDTO(repository.findById(id)
		.orElseThrow(() -> new ObjectNotFoundException("Object not found on the database")));
    }

    public List<CarDTO> findByType(CarType type) {
	return repository.findByType(type).stream().map(x -> new CarDTO(x)).collect(Collectors.toList());
    }

    public CarDTO save(CarDTO dto) {
	Car car = fromDTO(dto);
	return new CarDTO(repository.save(car));
    }

    public CarDTO update(Long id, CarDTO fromBody) throws ObjectNotFoundException {
	return repository.findById(id).map(fromDatabase -> {
	    updateData(fromBody, fromDatabase);
	    return this.save(new CarDTO(fromDatabase));
	}).orElseThrow(() -> new ObjectNotFoundException("Object not found on the database"));
    }

    public void deleteById(Long id) throws ObjectNotFoundException {
	this.findById(id);
	repository.deleteById(id);
    }

    public void updateData(CarDTO source, Car target) {
	BeanUtils.copyProperties(source, target, "id");
    }

    private Car fromDTO(CarDTO dto) {
	return new Car(dto.getId(), dto.getName(), dto.getType());
    }
}
