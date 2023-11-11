package edu.hw5.task6;

import org.jetbrains.annotations.NotNull;

public class SubstringUtils {

    private SubstringUtils() {
    }

    public static boolean isSubstring(@NotNull String str, @NotNull String substr) {
        return str.matches(
            ".*" + substr + ".*"
        );
    }
}
