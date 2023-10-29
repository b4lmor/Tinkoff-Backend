package edu.hw4.error.impl;

import edu.hw4.error.ValidationError;

public class TooSmallDogError implements ValidationError {

    private static final TooSmallDogError TOO_SMALL_DOG = new TooSmallDogError();

    private TooSmallDogError() {
    }

    public static TooSmallDogError get() {
        return TOO_SMALL_DOG;
    }

    @Override
    public String getMessage() {
        return "Dog can't be that short.";
    }

}
