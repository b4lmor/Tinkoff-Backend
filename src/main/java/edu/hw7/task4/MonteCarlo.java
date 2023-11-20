package edu.hw7.task4;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MonteCarlo {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int RADIUS = 1;
    private static final int THREAD_NUMBER = 3;

    private long totalCount = 0;
    private AtomicLong circleCount = new AtomicLong(0);

    public double countOneThread(long accuracy) {
        totalCount = accuracy;

        for (int s = 0; s < accuracy; s++) {
            Point point = generateRandomPoint();
            if (isPointInCircle(point)) {
                circleCount.incrementAndGet();
            }
        }

        return countPi(totalCount, circleCount.get());
    }

    public double countThreaded(long accuracy) {
        circleCount = new AtomicLong(0);
        totalCount = accuracy;

        Thread t1 = new Thread(() -> {
            for (int s = 0; s < accuracy / THREAD_NUMBER; s++) {
                Point point = generateRandomPoint();
                if (isPointInCircle(point)) {
                    circleCount.incrementAndGet();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int s = 0; s < accuracy / THREAD_NUMBER; s++) {
                Point point = generateRandomPoint();
                if (isPointInCircle(point)) {
                    circleCount.incrementAndGet();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int s = 0;
                 s < accuracy / THREAD_NUMBER + accuracy % THREAD_NUMBER; s++) {
                Point point = generateRandomPoint();
                if (isPointInCircle(point)) {
                    circleCount.incrementAndGet();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();

        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        }

        return countPi(totalCount, circleCount.get());
    }

    private boolean isPointInCircle(Point point) {
        return point.x * point.x
            + point.y * point.y <= RADIUS * RADIUS;
    }

    private Point generateRandomPoint() {
        SecureRandom random = new SecureRandom();

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
