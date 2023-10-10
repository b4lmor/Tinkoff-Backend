package edu.hw1.task4;

import org.jetbrains.annotations.NotNull;

public class StringFixer {

    private StringFixer() {
    }

    public static String fixString(@NotNull String brokenString) {
        if (brokenString.length() <= 1) {
            return brokenString;
        }

        StringBuilder fixedString = new StringBuilder();

        for (int i = 1; i < brokenString.length(); i += 2) {
            char firstLetter = brokenString.charAt(i - 1);
            char secondLetter = brokenString.charAt(i);
            fixedString.append(secondLetter);
            fixedString.append(firstLetter);
        }

        if (brokenString.length() % 2 != 0) {
            fixedString.append(brokenString.charAt(brokenString.length() - 1));
        }

        return fixedString.toString();
    }
}
