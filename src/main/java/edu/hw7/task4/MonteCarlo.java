package edu.hw7.task4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MonteCarlo {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TWO = 2;
    private static final int RADIUS = 1;

    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final AtomicLong atomicCircleCount = new AtomicLong(0);
    private long defaultCircleCount = 0;

    public double countOneThread(long accuracy) {

        for (int s = 0; s < accuracy; s++) {
            Point point = generateRandomPoint();
            if (isPointInCircle(point)) {
                defaultCircleCount++;
            }
        }

        return countPi(accuracy, defaultCircleCount);
    }


    public double countWithThreadPool(int accuracy) {

        var threadNumber = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
        CountDownLatch latch = new CountDownLatch(threadNumber);

        for (int s = 0; s < threadNumber; s++) {
            executor.submit(() -> {
                for (int k = 0; k < accuracy / threadNumber; k++) {
                    if (isPointInCircle(generateRandomPoint())) {
                        atomicCircleCount.incrementAndGet();
                    }
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        }

        executor.close();
        return countPi(accuracy, atomicCircleCount.get());
    }

    private boolean isPointInCircle(Point point) {
        return Math.pow(point.x, TWO)
            + Math.pow(point.y, TWO) <= RADIUS * RADIUS;
    }

    private Point generateRandomPoint() {
        double x = random.nextDouble() * RADIUS;
        double y = random.nextDouble() * RADIUS;

        return new Point(x, y);
    }

    @SuppressWarnings("MagicNumber")
    private double countPi(long totalCount, long circleCount) {
        return 4 * ((double) circleCount) / totalCount;
    }

    private record Point(
        double x,
        double y
    ) {
    }
}
