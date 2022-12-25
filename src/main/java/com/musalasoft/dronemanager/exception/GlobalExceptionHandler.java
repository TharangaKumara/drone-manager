package com.musalasoft.dronemanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({DataNotFoundException.class, IllegalArgumentException.class, DroneNotAvailableException.class})
    public Map<String, String> droneValidationExceptionHandler(RuntimeException ex) {
        LOGGER.error(ex.getMessage());
        return Collections.singletonMap("error", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ExceedMaximumWeightLimitException.class})
    public Map<String, String> exceedMaximumWeightLimitExceptionHandler(RuntimeException ex) {
        LOGGER.error(ex.getMessage());
        return Collections.singletonMap("error", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            LOGGER.error("Error in Field : [{}] - [{}] ", fieldError.getField(), fieldError.getDefaultMessage());
        });

        return errorMap;
    }


}