package com.rayllanderson.cars.api.util;

import org.springframework.core.convert.converter.Converter;

import com.rayllanderson.cars.domain.entities.enums.CarType;

public class StringToEnumConverter implements Converter<String, CarType>  {

    @Override
    public CarType convert(String source) {
	return CarType.valueOf(source.toUpperCase());
    }

}
