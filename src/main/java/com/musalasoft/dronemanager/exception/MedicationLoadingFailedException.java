package com.musalasoft.dronemanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedicationLoadingFailedException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationLoadingFailedException.class);

    public MedicationLoadingFailedException(String message, String error){
        super(message);
        LOGGER.error(message, error);
    }
}
