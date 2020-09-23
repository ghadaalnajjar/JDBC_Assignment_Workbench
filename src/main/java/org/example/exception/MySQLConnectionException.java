package org.example.exception;

public class MySQLConnectionException extends Throwable {
    private String message;

    public MySQLConnectionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
