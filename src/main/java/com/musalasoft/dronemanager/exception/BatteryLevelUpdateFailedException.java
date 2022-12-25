package com.musalasoft.dronemanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatteryLevelUpdateFailedException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatteryLevelUpdateFailedException.class);

    public BatteryLevelUpdateFailedException(String message, String error){
        super(message);
        LOGGER.error(message, error);
    }
}
