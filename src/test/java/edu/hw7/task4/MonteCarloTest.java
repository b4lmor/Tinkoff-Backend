package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void testNonThreadCountPi() {
        long startTime = System.nanoTime();

        double pi = new MonteCarlo().countOneThread(500000);

        long endTime = System.nanoTime();

        LOGGER.info("One thread: " + (endTime - startTime) / 1000000000.0);
        LOGGER.info("Pi = " + pi);


        startTime = System.nanoTime();

        pi = new MonteCarlo().countThreaded(500000);

        endTime = System.nanoTime();

        LOGGER.info("Three threads: " + (endTime - startTime) / 1000000000.0);
        LOGGER.info("Pi = " + pi);
    }
}
