package com.ing.casestudy.exception;

import java.io.Serializable;

public class CustomerDoesNotOwnTheLoanException extends RuntimeException implements Serializable {

    public CustomerDoesNotOwnTheLoanException() {}

    public CustomerDoesNotOwnTheLoanException(String message) {
        super(message);
    }

    public CustomerDoesNotOwnTheLoanException(String message, Throwable cause) {
        super(message, cause);
    }
}
