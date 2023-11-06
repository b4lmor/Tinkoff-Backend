package edu.hw5.task8;

import org.jetbrains.annotations.NotNull;

public class AdvancedBinaryUtils {

    private AdvancedBinaryUtils() {
    }

    public static boolean hasOddLength(@NotNull String binary) {
        return binary.matches(
            "([01]{2})*[01]"
        );
    }

    public static boolean doesNotContainSerialOnes(@NotNull String binary) {
        return binary.matches(
            "(?!.*11.*)[01]+"
        );
    }

    public static boolean doesNotContainTowOrThreeOnes(@NotNull String binary) {
        return binary.matches(
            "(?!.*(11|111).*)[01]+"
        );
    }

    public static boolean everyOddDigitIsOne(@NotNull String binary) {
        return binary.matches(
            "1([01]1)*"
        );
    }
}
