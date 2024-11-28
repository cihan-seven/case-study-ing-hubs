package com.ing.casestudy.exception;

import java.io.Serializable;

public class InterestRateNotInRangeError extends RuntimeException implements Serializable {

    public InterestRateNotInRangeError() {}

    public InterestRateNotInRangeError(String message) {
        super(message);
    }

    public InterestRateNotInRangeError(String message, Throwable cause) {
        super(message, cause);
    }
}
