package ua.com.gelius.test.exception;

public enum ErrorMessage {
    NOT_FOUND_ID("MOdel not found with id = ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
