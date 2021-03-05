package com.rayllanderson.cars;

import com.rayllanderson.cars.api.CarController;
import com.rayllanderson.cars.domain.dto.CarDTO;
import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.repositories.CarRepository;
import com.rayllanderson.cars.domain.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CarsAPITests {

    @InjectMocks
    CarController controller;

    @Mock
    CarService service;

    @Mock
    CarRepository repository;

    private final String URL = "/api/v1.0/cars/";


    @Test
    void create() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.save(any(CarDTO.class))).thenReturn(new CarDTO());

        Car car = new Car(null, "Corsa", null, null, null, null, null, CarType.CLASSIC);
        ResponseEntity<Void> responseEntity = controller.save(CarDTO.create(car));

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        //        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
        //        List<CarDTO> cars = getCars(URL).getBody();
        //        assert cars != null;
        //        assertEquals(30, cars.size());
    }

}








