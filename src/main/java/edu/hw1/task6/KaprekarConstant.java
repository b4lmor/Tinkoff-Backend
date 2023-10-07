package edu.hw1.task6;

import edu.hw1.task2.DigitCounter;
import java.util.Arrays;

public class KaprekarConstant {

    private static final int MIN_VALUE_FOR_COUNTK_FUNCTION = 1001;
    private static final int MAX_VALUE_FOR_COUNTK_FUNCTION = 9999;
    private static final int BASE_VALUE = 10;
    private static final int KAPREKAR_CONSTANT = 6174;

    private KaprekarConstant() {
        throw new IllegalStateException("Utility class");
    }

    private static int[] splitNumber(int number) {
        if (number == 0) {
            return new int[]{1};
        }

        int[] splitDigits = new int[DigitCounter.countDigits(number)];
        int ind = 0;
        int numberCopy = number;

        while (numberCopy != 0) {
            splitDigits[ind++] = numberCopy % BASE_VALUE;
            numberCopy /= BASE_VALUE;
        }

        return splitDigits;
    }

    public static int countK(int number) {
        if (number < MIN_VALUE_FOR_COUNTK_FUNCTION || number > MAX_VALUE_FOR_COUNTK_FUNCTION) {
            throw new IllegalArgumentException();
        }

        int[] digits = splitNumber(number);
        Arrays.sort(digits);

        int ascendingNumber = 0;
        int descendingNumber = 0;

        for (int digit : digits) {
            ascendingNumber = ascendingNumber * BASE_VALUE + digit;
        }

        for (int i = digits.length - 1; i >= 0; i--) {
            descendingNumber = descendingNumber * BASE_VALUE + digits[i];
        }

        int result = descendingNumber - ascendingNumber;

        if (result == KAPREKAR_CONSTANT) {
            return 1;
        }

        return 1 + countK(result);
    }
}
