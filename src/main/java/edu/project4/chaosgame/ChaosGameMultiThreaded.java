package edu.project4.chaosgame;

import edu.project4.entity.AffineColor;
import edu.project4.entity.FractalImage;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChaosGameMultiThreaded implements ChaosGame {
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

        ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );

        if (affineTransformations == null) {
            affineTransformations = AffineTransformation.getRandomAffineTransformations(samples);
        }
        if (affineColors == null) {
            affineColors = AffineColor.getRandomAffineColors(samples);
        }

        ChaosGameUtils.SynchronizedProgressBar pb = new ChaosGameUtils.SynchronizedProgressBar(
            "Building fractal (Multi-threaded)...",
            (long) samples * iterationPerSample
        );

        CountDownLatch latch = new CountDownLatch(samples);

        var startTime = System.nanoTime();
        pb.start();

        for (int num = 0; num < samples; num++) {
            executor.submit(() -> {
                ChaosGameUtils.doSubIteration(
                    fractalTransformations,
                    iterationPerSample, symmetry,
                    drawArea, canvas,
                    affineTransformations, affineColors,
                    pb,
                    START,
                    latch
                );
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

        var endTime = System.nanoTime();
        pb.stop();

        executor.shutdownNow();

        LOGGER.info("Multi-threaded fractal generating time: {}.",
            (endTime - startTime) / NANO);

        return canvas;
    }

}
