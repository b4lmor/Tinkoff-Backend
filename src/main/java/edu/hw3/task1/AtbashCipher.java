package edu.hw3.task1;

import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AtbashCipher {
    private static final int ALPHABET_SIZE = 26;
    private static final char FIRST_LETTER_UPPERCASE = 'A';
    private static final char FIRST_LETTER_LOWERCASE = 'a';

    private AtbashCipher() {
    }

    public static String encryptString(@NotNull String str) {
        return str.chars()
            .mapToObj(c -> (char) c)
            .map(AtbashCipher::encryptChar)
            .map(Object::toString)
            .collect(Collectors.joining());
    }

    private static char encryptChar(char c) {
        if (!Character.isAlphabetic(c)) {
            return c;
        }

        int base = (int) (
            Character.isUpperCase(c)
            ? FIRST_LETTER_UPPERCASE
            : FIRST_LETTER_LOWERCASE
        );

        return (char) (base + (ALPHABET_SIZE - ((int) c - base)) - 1);
    }
}
