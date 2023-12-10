package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonteCarloTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ACCURACY = 50_000_000;
    private static final double NANO = 1_000_000_000.0;

    @Test
    void testNonThreadCountPi() {
        long startTime = System.nanoTime();

        double pi = new MonteCarlo().countOneThread(ACCURACY);

        long endTime = System.nanoTime();

        LOGGER.info("One thread: {}", (endTime - startTime) / NANO);
        LOGGER.info("Pi = {}\n", pi);

        assertTrue(
            Math.abs(Math.PI - pi) < 0.1
        );



        startTime = System.nanoTime();

        pi = new MonteCarlo().countWithThreadPool(ACCURACY);

        endTime = System.nanoTime();

        LOGGER.info("Multi threads (with thread pool): {}", (endTime - startTime) / NANO);
        LOGGER.info("Pi = {}\n", pi);

        assertTrue(
            Math.abs(Math.PI - pi) < 0.01
        );

    }
}
