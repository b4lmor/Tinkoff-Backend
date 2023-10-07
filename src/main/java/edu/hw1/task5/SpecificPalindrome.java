package edu.hw1.task5;

import edu.hw1.task2.DigitCounter;

public class SpecificPalindrome {

    private static final int BASE_NUMBER = 10;

    private SpecificPalindrome() {
        throw new IllegalStateException("Utility class");
    }

    private static boolean isPalindrome(int number) {
        int numberCopy = number;
        int reversedNumber = 0;

        while (numberCopy > 0) {
            int digit = numberCopy % BASE_NUMBER;
            reversedNumber = reversedNumber * BASE_NUMBER + digit;
            numberCopy /= BASE_NUMBER;
        }

        return number == reversedNumber;
    }

    private static int findDescendant(int number) {
        int numberCopy = number;
        int descendant = 0;
        int multiplier = 1;

        while (numberCopy > BASE_NUMBER - 1) {
            int digit1 = numberCopy % BASE_NUMBER;
            int digit2 = (numberCopy / BASE_NUMBER) % BASE_NUMBER;
            int sum = digit1 + digit2;
            descendant += sum * multiplier;
            numberCopy /= (BASE_NUMBER * BASE_NUMBER);
            multiplier *= BASE_NUMBER;
        }

        return descendant;
    }

    private static boolean hasEvenLength(int number) {
        return DigitCounter.countDigits(number) % 2 == 0;
    }

    public static boolean isPalindromeDescendant(int number) {
        if (number < 0) {
            return isPalindromeDescendant(Math.abs(number));
        }
        if (number < BASE_NUMBER) {
            return false;
        }
        if (!hasEvenLength(number)) {
            return isPalindrome(number);
        }
        return isPalindrome(number) || isPalindromeDescendant(findDescendant(number));
    }



}
