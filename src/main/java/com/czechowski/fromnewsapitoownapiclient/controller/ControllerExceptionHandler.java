package com.czechowski.fromnewsapitoownapiclient.controller;

import com.czechowski.fromnewsapitoownapiclient.model.error.BadRequestError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleUnsupportedCountry(HttpClientErrorException exception) {

        ObjectMapper objectMapper = new ObjectMapper();

        BadRequestError badRequestError = null;
        try {
            badRequestError = objectMapper.readValue(exception.getResponseBodyAsString(), BadRequestError.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(badRequestError, HttpStatus.BAD_REQUEST);
    }
}
