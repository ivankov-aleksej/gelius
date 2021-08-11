package ua.com.gelius.test.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("Application error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST),
    DATA_NOT_FOUND("Data not found", HttpStatus.NOT_FOUND);


    private final String errorMessage;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorMessage = errorCode;
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
