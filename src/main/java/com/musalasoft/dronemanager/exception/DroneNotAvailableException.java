package com.musalasoft.dronemanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroneNotAvailableException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneNotAvailableException.class);

    public DroneNotAvailableException(String message){
        super(message);
        LOGGER.error(message);
    }
}
