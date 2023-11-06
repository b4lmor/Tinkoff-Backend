package edu.hw5.task4;

import org.jetbrains.annotations.NotNull;

public class PasswordUtils {

    private PasswordUtils() {
    }

    public static boolean isValid(@NotNull String password) {
        return password.matches(
            ".*[~!@#$%^&*|].*"
        );
    }
}
