package com.ing.casestudy.exception;

import java.io.Serializable;

public class LoanAlreadyPaidException extends RuntimeException implements Serializable {

    public LoanAlreadyPaidException() {}

    public LoanAlreadyPaidException(String message) {
        super(message);
    }

    public LoanAlreadyPaidException(String message, Throwable cause) {
        super(message, cause);
    }
}
