package edu.hw2.task3.exception;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
    }

    public ConnectionException(String cause) {
        super(cause);
    }
}
