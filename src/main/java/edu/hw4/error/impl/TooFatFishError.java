package edu.hw4.error.impl;

import edu.hw4.error.ValidationError;

public class TooFatFishError implements ValidationError {

    private static final TooFatFishError TOO_FAT_FISH = new TooFatFishError();

    private TooFatFishError() {
    }

    public static TooFatFishError get() {
        return TOO_FAT_FISH;
    }

    @Override
    public String getMessage() {
        return "Fish can't be that fat.";
    }

}
