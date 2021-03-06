package com.rayllanderson.cars.api.exceptions;

import com.rayllanderson.cars.domain.entities.enums.CarType;
import com.rayllanderson.cars.domain.service.exceptions.ObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<StandardError> handleConflict(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String URI = request.getRequestURI();
        String typedType = URI.split("/type")[1];
        String message = "There is no type of " + typedType + ". Available types: " + Arrays.asList(CarType.values());
        StandardError err = new StandardError(status.value(), "Bad Request", message, URI);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFound(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(status.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleIllegalArgument(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(status.value(), "Bad Request", e.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleEnumInvalid(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = e.getMessage().split(";")[0];
        StandardError err = new StandardError(status.value(), "Bad Request", message, request.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    /*
    pegando uma exception que não dá pra pegar com @ExceptionHandler
    extends ResponseEntityExceptionHandler then ->
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders
    headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(new HttpRequestMethodNotSupportedException("Method not allowed", "Not
        Allowed"), headers,
                status, request);
    }*/
}
