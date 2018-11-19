package com.itacademy.service.exceptions;

public class ServiceLayerException extends RuntimeException {

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
