package edu.hw1.task3;

import org.jetbrains.annotations.NotNull;

public class Range {
    private final int leftBorder;
    private final int rightBorder;

    public Range(int @NotNull [] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }

        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);
        }

        leftBorder = min;
        rightBorder = max;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }
}
