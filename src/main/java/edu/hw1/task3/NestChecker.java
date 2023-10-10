package edu.hw1.task3;

public class NestChecker {
    private static final int MIN_LENGTH_OF_ARRAY = 2;

    private NestChecker() {
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        if (isNotValidArray(array1) || isNotValidArray(array2)) {
            throw new IllegalArgumentException();
        }

        Range arrayRange1 = new Range(array1);
        Range arrayRange2 = new Range(array2);

        return (arrayRange1.getLeftBorder() > arrayRange2.getLeftBorder())
            && (arrayRange1.getRightBorder() < arrayRange2.getRightBorder());
    }

    private static boolean isNotValidArray(int[] array) {
        return array == null
            || array.length < MIN_LENGTH_OF_ARRAY;
    }
}
