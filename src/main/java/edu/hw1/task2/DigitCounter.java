package edu.hw1.task2;

public class DigitCounter {

    private static final int VALUE_OF_NUMBER_SYSTEM = 10;

    private DigitCounter() {
        throw new IllegalStateException("Utility class");
    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int absNumber = Math.abs(number);
        int digitCounter = 0;

        while (absNumber != 0) {
            absNumber /= VALUE_OF_NUMBER_SYSTEM;
            digitCounter++;
        }
        return digitCounter;
    }
}
