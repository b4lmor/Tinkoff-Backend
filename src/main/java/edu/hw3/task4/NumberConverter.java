package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class NumberConverter {
    private static final LinkedHashMap<String, Integer> ROMAN_NUMERALS;

    static {
        ROMAN_NUMERALS = new LinkedHashMap<>();
        ROMAN_NUMERALS.put("M", 1000);
        ROMAN_NUMERALS.put("CM", 900);
        ROMAN_NUMERALS.put("D", 500);
        ROMAN_NUMERALS.put("CD", 400);
        ROMAN_NUMERALS.put("C", 100);
        ROMAN_NUMERALS.put("XC", 90);
        ROMAN_NUMERALS.put("L", 50);
        ROMAN_NUMERALS.put("XL", 40);
        ROMAN_NUMERALS.put("X", 10);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("I", 1);
    }

    private NumberConverter() {
    }

    public static String arabicToRoman(int number) {

        int numberCopy = number;
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Integer> entry : ROMAN_NUMERALS.entrySet()) {
            while (numberCopy >= entry.getValue()) {
                result.append(entry.getKey());
                numberCopy -= entry.getValue();
            }
        }

        return result.toString();
    }
}
