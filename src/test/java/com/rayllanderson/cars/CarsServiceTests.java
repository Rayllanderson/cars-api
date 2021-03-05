package com.rayllanderson.cars;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rayllanderson.cars.domain.dto.CarDTO;
import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.service.CarService;
import com.rayllanderson.cars.domain.service.exceptions.ObjectNotFoundException;

@SpringBootTest
class CarsServiceTests {

    @Autowired
    private CarService service;

    @Test
    void crud() {
	Car car = new Car(null, "Ferrari", null, null, null, null, null, CarType.SPORTING);

	CarDTO dto = service.save(CarDTO.create(car));

	assertNotNull(dto);

	final Long CAR_ID = dto.getId();

	assertNotNull(service.findById(CAR_ID));
	assertEquals(dto.getName(), "Ferrari");
	assertEquals(dto.getType(), CarType.SPORTING);

	dto.setType(CarType.LUXE);
	dto = service.update(CAR_ID, dto);
	assertEquals(dto.getType(), CarType.LUXE);

	service.deleteById(dto.getId());
	assertThatThrownBy(() -> {
	    service.findById(CAR_ID);
	}).isInstanceOf(ObjectNotFoundException.class);
	
	assertEquals("Lamborghini Reventon", service.findById(26L).getName());
    }

    @Test
    void test2() {
	List<CarDTO> cars = service.findAll();
	cars.forEach(System.out::println);
	assertTrue(cars.size() == 30);
    }
}
