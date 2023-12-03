package edu.project4.chaosgame;

import edu.project4.entity.AffineColor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Rotation;
import edu.project4.transformation.Transformation;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class ChaosGameUtils {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private ChaosGameUtils() {
    }

    public static <T> T peekRandom(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static <T, M> Pair<T, M> peekRandomPair(T[] array1, M[] array2) {
        int index = RANDOM.nextInt(
            Math.min(array1.length, array2.length));
        return new Pair<>(array1[index], array2[index]);
    }

    public static Vector2D getRandomPoint(Rectangle2D.Double drawArea) {
        double x = drawArea.getX() + RANDOM.nextDouble() * drawArea.getWidth();
        double y = drawArea.getY() + RANDOM.nextDouble() * drawArea.getHeight();
        return new Vector2D(x, y);
    }

    public static void setPixelColor(Pixel pixel, Color color) {
        if (pixel.getHitCount() == 0) {
            pixel.setRed(color.getRed());
            pixel.setGreen(color.getGreen());
            pixel.setBlue(color.getBlue());

        } else {
            pixel.setRed((pixel.getRed() + color.getRed()) / 2);
            pixel.setGreen((pixel.getGreen() + color.getGreen()) / 2);
            pixel.setBlue((pixel.getBlue() + color.getBlue()) / 2);
        }
    }

    @SuppressWarnings("ParameterNumber")
    public static void doSubIteration(
        List<Transformation> fractalTransformations,
        int iterationPerSample, int symmetry,
        Rectangle2D.Double drawArea, FractalImage canvas,
        AffineTransformation[] affineTransformations,
        AffineColor[] affineColors,
        SynchronizedProgressBar pb,
        int start,
        CountDownLatch latch
    ) {
        Vector2D pw = ChaosGameUtils.getRandomPoint(drawArea);

        for (int step = start; step < iterationPerSample; step++) {

            ChaosGameUtils.Affine affine
                = new ChaosGameUtils.Affine(
                ChaosGameUtils.peekRandomPair(affineTransformations, affineColors));

            Transformation transformation =
                ChaosGameUtils.peekRandom(fractalTransformations);

            pw = affine.transformation().apply(pw);
            pw = transformation.apply(pw);

            if (step <= 0) {
                continue;
            }

            double theta = 0;
            for (int s = 0; s < symmetry; s++) {
                theta += 2 * Math.PI / symmetry;
                Rotation rotation = new Rotation(theta);
                Vector2D pwr = rotation.apply(pw);

                if (!drawArea.contains(pwr.getX(), pwr.getY())) {
                    continue;
                }

                Pixel pixel = canvas.getPixel(
                    (int) ((pwr.getX() - drawArea.x) * canvas.getWidth() / drawArea.width),
                    (int) ((pwr.getY() - drawArea.y) * canvas.getHeight() / drawArea.height)
                );

                if (pixel == null) {
                    continue;
                }

                ChaosGameUtils.setPixelColor(pixel, affine.color());
                pixel.incrementHitCount();
            }

            if (latch != null) {
                latch.countDown();
            }

            pb.syncStep();
        }
    }

    public record Affine(
        AffineTransformation transformation,
        Color color
    ) {
        public Affine(Pair<AffineTransformation, AffineColor> pair) {
            this(pair.getFirst(), pair.getSecond().color());
        }
    }

    public static class SynchronizedProgressBar extends ProgressBar {
        public SynchronizedProgressBar(String task, long initialMax) {
            super(task, initialMax);
        }

        synchronized public void syncStep() {
            super.step();
        }
    }
}
