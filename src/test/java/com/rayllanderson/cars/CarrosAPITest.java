package com.rayllanderson.cars;

import com.rayllanderson.cars.domain.dto.CarDTO;
import com.rayllanderson.cars.domain.entities.Car;
import com.rayllanderson.cars.domain.entities.enums.CarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest extends BaseAPITest {

    private ResponseEntity<CarDTO> getCarro(String url) {
        return get(url, CarDTO.class);
    }

    private ResponseEntity<List<CarDTO>> getCarros(String url) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<CarDTO>>() {
                });
    }

    @Test
    public void testSave() {

        Car carro = new Car();
        carro.setName("Porshe");
        carro.setType(CarType.SPORTING);

        // Insert
        ResponseEntity response = post("/api/v1.0/cars", carro, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        CarDTO c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getName());
        assertEquals(CarType.SPORTING, c.getType());

        // Deletar o objeto
        delete(location, null);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

//    @Test
//    public void testLista() {
//        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
//        assertNotNull(carros);
//        assertEquals(30, carros.size());
//    }

//    @Test
//    public void testListaPorTipo() {
//        assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
//        assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
//        assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());
//
//        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
//    }

    @Test
    public void testGetOk() {

        ResponseEntity<CarDTO> response = getCarro("/api/v1.0/cars/5");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarDTO c = response.getBody();
        assertNotNull(c);
        assertEquals("Chevrolet Corvette", c.getName());
    }

    @Test
    public void testGetNotFound() {

        ResponseEntity response = getCarro("/api/v1.0/cars/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}