package com.ing.casestudy.exception;

import java.io.Serializable;

public class CustomerLimitExceedError extends RuntimeException implements Serializable {

    public CustomerLimitExceedError() {}

    public CustomerLimitExceedError(String message) {
        super(message);
    }

    public CustomerLimitExceedError(String message, Throwable cause) {
        super(message, cause);
    }
}
