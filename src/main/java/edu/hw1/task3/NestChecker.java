package edu.hw1.task3;

import java.util.Arrays;

public class NestChecker {
    private static final int MIN_LENGTH_OF_ARRAY = 2;

    private NestChecker() {
    }

    private static int findMin(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(array).min().getAsInt();
    }

    private static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(array).max().getAsInt();
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        if (array1.length < MIN_LENGTH_OF_ARRAY || array2.length < MIN_LENGTH_OF_ARRAY) {
            throw new IllegalArgumentException();
        }
        return (findMin(array1) > findMin(array2)) && (findMax(array1) < findMax(array2));
    }
}
