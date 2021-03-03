package com.rayllanderson.cars.api.exceptions;

import java.time.Instant;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rayllanderson.cars.domain.entities.enums.CarType;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<StandardError> handleConflict(RuntimeException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	String URI = request.getRequestURI();
	String typedType = URI.split("/type")[1];
	String message = "There is no type of " + typedType + ". Available types: "
		+ Arrays.asList(CarType.values());
	
	StandardError err = new StandardError(Instant.now(), status.value(), "Bad Request", message, URI);
	return ResponseEntity.status(status).body(err);
    }
}
