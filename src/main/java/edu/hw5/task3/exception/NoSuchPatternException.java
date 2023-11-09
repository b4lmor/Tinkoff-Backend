package edu.hw5.task3.exception;

public class NoSuchPatternException extends RuntimeException {

    private static final String message = "Wrong pattern";

    public NoSuchPatternException() {
        super(message);
    }
}
