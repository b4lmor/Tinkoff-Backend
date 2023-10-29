package edu.hw4.error.impl;

import edu.hw4.error.ValidationError;

public class BigBonedFishError implements ValidationError {

    private static final BigBonedFishError TOO_FAT_FISH = new BigBonedFishError();

    private BigBonedFishError() {
    }

    public static BigBonedFishError get() {
        return TOO_FAT_FISH;
    }

    @Override
    public String getMessage() {
        return "Fish can't be that fat.";
    }

}
