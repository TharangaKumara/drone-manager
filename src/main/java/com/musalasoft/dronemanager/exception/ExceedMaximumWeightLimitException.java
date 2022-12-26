package com.musalasoft.dronemanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceedMaximumWeightLimitException extends RuntimeException{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceedMaximumWeightLimitException.class);

    public ExceedMaximumWeightLimitException(String message){
        super(message);
        LOGGER.error(message);
    }
}
