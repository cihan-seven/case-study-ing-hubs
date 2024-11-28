package com.ing.casestudy.exception;

import java.io.Serializable;

public class InsufficientLoanAmountException extends RuntimeException implements Serializable {

    public InsufficientLoanAmountException() {}

    public InsufficientLoanAmountException(String message) {
        super(message);
    }

    public InsufficientLoanAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
