package edu.hw5.task5;

import org.jetbrains.annotations.NotNull;

public class CarPlateUtils {

    private CarPlateUtils() {
    }

    public static boolean isValid(@NotNull String carPlate) {
        return carPlate.matches(
            "[A-Z]\\d{3}[A-Z]{2}\\d{3}"
        );
    }
}
