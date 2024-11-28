package com.ing.casestudy.exception;

import java.io.Serializable;

public class NumberOfInstallmentsMatchError extends RuntimeException implements Serializable {

    public NumberOfInstallmentsMatchError() {}

    public NumberOfInstallmentsMatchError(String message) {
        super(message);
    }

    public NumberOfInstallmentsMatchError(String message, Throwable cause) {
        super(message, cause);
    }
}
