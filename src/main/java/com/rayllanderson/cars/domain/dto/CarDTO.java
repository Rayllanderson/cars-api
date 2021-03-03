package com.rayllanderson.cars.domain.dto;

import org.modelmapper.ModelMapper;

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

    public static CarDTO create(Car car) {
	ModelMapper modelMapper = new ModelMapper();
	return modelMapper.map(car, CarDTO.class);
    }

}
