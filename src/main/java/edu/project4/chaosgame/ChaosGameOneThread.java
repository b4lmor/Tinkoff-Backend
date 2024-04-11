package edu.project4.chaosgame;

import edu.project4.entity.AffineColor;
import edu.project4.entity.FractalImage;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChaosGameOneThread implements ChaosGame {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double NANO = 1_000_000_000.0;
    private static final int START = -20;

    private AffineTransformation[] affineTransformations = null;
    private AffineColor[] affineColors = null;

    @Override
    public FractalImage iterate(
        List<Transformation> fractalTransformations,
        int samples, int iterationPerSample, int symmetry,
        Rectangle2D.Double drawArea, FractalImage canvas
    ) {

        if (affineTransformations == null) {
            affineTransformations = AffineTransformation.getRandomAffineTransformations(samples);
        }

        if (affineColors == null) {
            affineColors = AffineColor.getRandomAffineColors(samples);
        }

        ChaosGameUtils.SynchronizedProgressBar pb = new ChaosGameUtils.SynchronizedProgressBar(
            "Building fractal (one thread)...",
            (long) samples * iterationPerSample
        );

        var startTime = System.nanoTime();
        pb.start();

        for (int num = 0; num < samples; num++) {
            ChaosGameUtils.doSubIteration(
                fractalTransformations,
                iterationPerSample, symmetry,
                drawArea, canvas,
                affineTransformations, affineColors,
                pb,
                START,
                null
            );
        }

        var endTime = System.nanoTime();
        pb.stop();

        LOGGER.info("One-thread fractal generating time: {}.", (endTime - startTime) / NANO);

        return canvas;
    }

}
