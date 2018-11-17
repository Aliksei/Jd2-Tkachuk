package com.itacademy.exceptions;

public class ScriptNotFoundException extends RuntimeException {

    public ScriptNotFoundException(String message) {
        super(message);
    }

    public ScriptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

