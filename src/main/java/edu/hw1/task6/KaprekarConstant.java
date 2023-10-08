package edu.hw1.task6;

import edu.hw1.task2.DigitCounter;
import java.util.Arrays;

public class KaprekarConstant {

    private static final int MIN_VALUE_FOR_COUNTK_FUNCTION = 1001;
    private static final int MAX_VALUE_FOR_COUNTK_FUNCTION = 9999;
    private static final int DIGITS_IN_NUMBER = 4;
    private static final int BASE_VALUE = 10;
    private static final int KAPREKAR_CONSTANT = 6174;

    private KaprekarConstant() {
        throw new IllegalStateException("Utility class");
    }

    private static int[] splitNumberTo4Digits(int number) {
        if (number == 0) {
            return new int[]{1};
        }

        int[] splitDigits = new int[DIGITS_IN_NUMBER];
        int ind = 0;
        int numberCopy = number;

        while (numberCopy != 0 && ind != 4) {
            splitDigits[ind++] = numberCopy % BASE_VALUE;
            numberCopy /= BASE_VALUE;
        }
        while (ind != 4) {
            splitDigits[ind++] = 0;
        }

        return splitDigits;
    }

    private static boolean allDigitsSame(int number) {
        int numberCopy = number;
        int lastDigit = numberCopy % 10;

        while (numberCopy != 0) {
            int digit = numberCopy % 10;

            if (digit != lastDigit) {
                return false;
            }

            numberCopy /= 10;
        }

        return true;
    }

    private static boolean isNumberValid(int number) {
        if (number < MIN_VALUE_FOR_COUNTK_FUNCTION || number > MAX_VALUE_FOR_COUNTK_FUNCTION) {
            return false;
        }
        if (allDigitsSame(number)) {
            return false;
        }
        return true;
    }

    private static int countKValid(int number) {
        int[] digits = splitNumberTo4Digits(number);
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

        return 1 + countKValid(result);
    }

    public static int countK(int number) {
        if (!isNumberValid(number)) {
            throw new IllegalArgumentException();
        }

        return countKValid(number);
    }
}
