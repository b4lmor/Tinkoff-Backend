package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    public static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(4, BigInteger.valueOf(24)),
            Arguments.of(10, BigInteger.valueOf(3628800)),
            Arguments.of(12, BigInteger.valueOf(479001600))
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void testFactorial(int input, BigInteger expectedResult) {
        BigInteger factorial = Factorial.count(input);

        assertEquals(
            expectedResult,
            factorial
        );
    }
}
