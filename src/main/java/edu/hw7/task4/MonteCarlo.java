package edu.hw7.task4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MonteCarlo {

    private static final Logger LOGGER = LogManager.getLogger();

    private final SecureRandom random = new SecureRandom();

    private static final int RADIUS = 1;
    private static final int THREAD_NUMBER = 3;

    private long defaultCircleCount = 0;
    private AtomicLong atomicCircleCount = new AtomicLong(0);

    public double countOneThread(long accuracy) {

        for (int s = 0; s < accuracy; s++) {
            Point point = generateRandomPoint();
            if (isPointInCircle(point)) {
                defaultCircleCount++;
            }
        }

        return countPi(accuracy, defaultCircleCount);
    }

    public double countThreaded(long accuracy) {
        atomicCircleCount = new AtomicLong(0);

        Thread t1 = new Thread(() -> {
            for (int s = 0; s < accuracy / THREAD_NUMBER; s++) {
                Point point = generateRandomPoint();
                if (isPointInCircle(point)) {
                    atomicCircleCount.incrementAndGet();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int s = 0; s < accuracy / THREAD_NUMBER; s++) {
                Point point = generateRandomPoint();
                if (isPointInCircle(point)) {
                    atomicCircleCount.incrementAndGet();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int s = 0;
                 s < accuracy / THREAD_NUMBER + accuracy % THREAD_NUMBER; s++) {
                Point point = generateRandomPoint();
                if (isPointInCircle(point)) {
                    atomicCircleCount.incrementAndGet();
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

        return countPi(accuracy, atomicCircleCount.get());
    }

    public double countWithThreadPool(int accuracy) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
        List<Runnable> tasks = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);

        for (int s = 0; s < accuracy; s++) {
            tasks.add(() -> {
                if (isPointInCircle(generateRandomPoint())) {
                    defaultCircleCount++;
                }
                latch.countDown();
            });
        }

        tasks.forEach(executor::submit);

        try {
            latch.await();

        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        }

        executor.close();
        return countPi(accuracy, defaultCircleCount);
    }

    private boolean isPointInCircle(Point point) {
        return point.x * point.x
            + point.y * point.y <= RADIUS * RADIUS;
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
