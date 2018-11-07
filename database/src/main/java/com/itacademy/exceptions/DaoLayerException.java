package com.itacademy.exceptions;

public class DaoLayerException extends RuntimeException {

    public DaoLayerException(String message) {
        super("Exception DAO layer" + message);
    }

    public DaoLayerException(String message, Throwable cause) {
        super("Exception DAO layer " + message, cause);
    }
}

