package com.rayllanderson.cars.domain.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rayllanderson.cars.domain.entities.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String password;

    // token jwt
    private String token;

    public static UserDTO create(User user, String token) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }

}
