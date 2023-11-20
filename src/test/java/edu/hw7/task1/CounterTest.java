package edu.hw7.task1;

import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(10000),
            Arguments.of(999),
            Arguments.of(3333333)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void testAtomicCounter(int input) {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < input / 2 + input % 2; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < input / 2; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            LOGGER.error("Can't join thread: " + e.getMessage());
        }

        assertEquals(
            input,
            counter.getCount()
        );

    }
}
