package com.ing.casestudy.exception;

import java.io.Serializable;

public class DatabaseObjectNotFoundError extends RuntimeException implements Serializable {

    public DatabaseObjectNotFoundError() {}

    public DatabaseObjectNotFoundError(String message) {
        super(message);
    }

    public DatabaseObjectNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
}
