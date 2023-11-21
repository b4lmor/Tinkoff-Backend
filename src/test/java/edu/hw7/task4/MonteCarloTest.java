package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ACCURACY = 500000;
    private static final double NANO = 1000000000.0;

    @Test
    void testNonThreadCountPi() {
        long startTime = System.nanoTime();

        double pi = new MonteCarlo().countOneThread(ACCURACY);

        long endTime = System.nanoTime();

        LOGGER.info("One thread: " + (endTime - startTime) / NANO);
        LOGGER.info("Pi = " + pi);


        startTime = System.nanoTime();

        pi = new MonteCarlo().countThreaded(ACCURACY);

        endTime = System.nanoTime();

        LOGGER.info("Three threads: " + (endTime - startTime) / NANO);
        LOGGER.info("Pi = " + pi);


        startTime = System.nanoTime();

        pi = new MonteCarlo().countWithThreadPool(ACCURACY);

        endTime = System.nanoTime();

        LOGGER.info("Three threads (with thread pool): " + (endTime - startTime) / NANO);
        LOGGER.info("Pi = " + pi);
    }
}
