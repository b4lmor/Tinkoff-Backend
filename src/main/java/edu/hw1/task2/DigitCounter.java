package edu.hw1.task2;

public class DigitCounter {

    private static final int BASE_VALUE = 10;

    private DigitCounter() {
    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int absNumber = Math.abs(number);
        int digitCounter = 0;

        while (absNumber != 0) {
            absNumber /= BASE_VALUE;
            digitCounter++;
        }

        return digitCounter;
    }
}
