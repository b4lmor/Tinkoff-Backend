package edu.hw1.task2;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DigitCounterTest {

    @ParameterizedTest
    @DisplayName("Count digits in number")
    @MethodSource("provideParameters")
    public void testDigitCounter(int input_number, int expectedResult) {
        final int countedDigits = DigitCounter.countDigits(input_number);
        assertThat(countedDigits)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(-9, 1),
            Arguments.of(30, 2),
            Arguments.of(-53, 2),
            Arguments.of(123456789, 9),
            Arguments.of(-123456789, 9)
        );
    }
}
