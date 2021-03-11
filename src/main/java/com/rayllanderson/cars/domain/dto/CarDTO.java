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
    private String description;
    private String urlPhoto;
    private String urlVideo;

    public static CarDTO create(Car car) {
        return new ModelMapper().map(car, CarDTO.class);
    }
}
