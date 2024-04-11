package edu.hw8.task3;

import java.util.concurrent.atomic.AtomicLong;

public class PasswordGenerator {

    private static final String ALPHABET =
              "1234567890"
            + "abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "+-/\\_()[]{}<>*&?!.,\"':$#@%~`";

    private static final int ALPHABET_SIZE = ALPHABET.length();

    private final AtomicLong index = new AtomicLong(0);

    public String generateNext() {
        index.incrementAndGet();

        StringBuilder password = new StringBuilder();
        long currentIndex = index.get();

        do {
            int remainder = (int) (currentIndex % ALPHABET_SIZE);
            password.append(ALPHABET.charAt(remainder));
            currentIndex /= ALPHABET_SIZE;

        } while (currentIndex > 0);

        return password.toString();
    }

}
