package com.musalasoft.dronemanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataNotFoundException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataNotFoundException.class);

    public DataNotFoundException(String message){
        super(message);
        LOGGER.error(message);
    }
}
