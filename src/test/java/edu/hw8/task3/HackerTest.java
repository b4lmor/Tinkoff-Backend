package edu.hw8.task3;

import edu.hw8.task3.hash.MD5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackerTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final double NANO = 1_000_000_000.0;

    private static Stream<Arguments> providePasswords() {
        return Stream.of(
            Arguments.of("pwd"),
            Arguments.of("pas"),
            Arguments.of("a1bb")
        );
    }

    @ParameterizedTest
    @MethodSource("providePasswords")
    void testPasswordHackOneThread(String password) {
        String hashed_password = MD5.getMd5(password);

        Hacker hacker = new Hacker();

        var startTime = System.nanoTime();

        String hacked_password = hacker.hackOneThread(hashed_password);

        var endTime = System.nanoTime();

        assertEquals(
            password,
            hacked_password
        );

        LOGGER.info("hack time: {}.", (endTime - startTime) / NANO);

    }

    @ParameterizedTest
    @MethodSource("providePasswords")
    void testPasswordHackMultiThreaded() {
        String password = "pwd";
        String hashed_password = MD5.getMd5(password);

        Hacker hacker = new Hacker();

        var startTime = System.nanoTime();

        String hacked_password = hacker.hackMultiThreaded(hashed_password);

        var endTime = System.nanoTime();

        assertEquals(
            password,
            hacked_password
        );

        LOGGER.info("hack time: {}.", (endTime - startTime) / NANO);
    }
}
