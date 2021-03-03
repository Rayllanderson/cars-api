package com.rayllanderson.cars.domain.dto;

import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {

    private Long id;
    private String name;
    private CarType type;

    public CarDTO(Car car) {
	this.id = car.getId();
	this.name = car.getName();
	this.type = car.getType();
    }

}
