package edu.project4.transformation;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class AffineTransformation implements Transformation {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private final Transformation linear;
    private final Transformation translation;

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.linear = new LinearTransformation(a, b, d, e);
        this.translation = new Translation(c, f);
    }

    public static AffineTransformation[] getRandomAffineTransformations(int samples) {
        AffineTransformation[] transformations = new AffineTransformation[samples];
        for (int i = 0; i < samples; i++) {
            transformations[i] = create();
        }
        return transformations;
    }

    public static AffineTransformation create() {
        while (true) {
            double a = RANDOM.nextDouble(-1, 1);
            double b = RANDOM.nextDouble(-1, 1);
            double c = RANDOM.nextDouble(-1, 1);
            double d = RANDOM.nextDouble(-1, 1);
            double e = RANDOM.nextDouble(-1, 1);
            double f = RANDOM.nextDouble(-1, 1);
            if (isCoefficientsValid(a, b, c, d, e, f)) {
                return new AffineTransformation(a, b, c, d, e, f);
            }
        }
    }

    private static boolean isCoefficientsValid(
        double a, double b, double c, double d, double e, double f
    ) {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1)
            && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
            && (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2)
            < 1 + (a * e - b * d) * (a * e - b * d));
    }

    @Override
    public Vector2D apply(Vector2D v) {
        return translation.apply(linear.apply(v));
    }

}
