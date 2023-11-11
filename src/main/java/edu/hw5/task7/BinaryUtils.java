package edu.hw5.task7;

import org.jetbrains.annotations.NotNull;

public class BinaryUtils {

    private BinaryUtils() {
    }

    public static boolean containsAtLeast3With0AtThirdPosition(@NotNull String binary) {
        return binary.matches(
            "[01]{2}0[01]*"
        );
    }

    public static boolean isStartEqualsToEnd(@NotNull String binary) {
        String start = binary.substring(0, 1);

        return binary.matches(
                start + "[01]*" + start
        );
    }

    public static boolean isLengthFrom1To3(@NotNull String binary) {
        return binary.matches(
                "[01]{1,3}"
        );
    }
}
