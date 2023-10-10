package edu.hw1.task3;

import org.jetbrains.annotations.NotNull;

public class NestChecker {
    private static final int MIN_LENGTH_OF_ARRAY = 2;

    private NestChecker() {
    }

    public static boolean isNestable(int @NotNull [] array1, int @NotNull [] array2) {
        if (array1.length < MIN_LENGTH_OF_ARRAY || array2.length < MIN_LENGTH_OF_ARRAY) {
            throw new IllegalArgumentException();
        }

        Range arrayRange1 = new Range(array1);
        Range arrayRange2 = new Range(array2);

        return (arrayRange1.getLeftBorder() > arrayRange2.getLeftBorder())
            && (arrayRange1.getRightBorder() < arrayRange2.getRightBorder());
    }

}
