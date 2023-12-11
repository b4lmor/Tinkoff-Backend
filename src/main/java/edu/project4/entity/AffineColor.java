package edu.project4.entity;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineColor(Color color) {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private static final int COLOR_BOUND = 256;

    public static AffineColor[] getRandomAffineColors(int samples) {
        AffineColor[] colors = new AffineColor[samples];
        for (int i = 0; i < samples; i++) {
            colors[i] = create();
        }
        return colors;
    }

    public static AffineColor create() {
        return new AffineColor(getRandomColor());
    }

    private static Color getRandomColor() {
        return new Color(
            RANDOM.nextInt(0, COLOR_BOUND),
            RANDOM.nextInt(0, COLOR_BOUND),
            RANDOM.nextInt(0, COLOR_BOUND)
        );
    }
}
