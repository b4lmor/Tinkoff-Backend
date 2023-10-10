package edu.hw1.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CyclicBitShifterTest {

    @ParameterizedTest
    @DisplayName("Do cyclic bit right shift in a number")
    @MethodSource("provideParametersForRightShift")
    public void testRotateRight(int inputNumber, int inputShift, int expectedResult) {
        final int shiftedNumber = CyclicBitShifter.rotateRight(inputNumber, inputShift);
        assertThat(shiftedNumber)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideParametersForRightShift() {
        return Stream.of(
            Arguments.of(8, 1, 4),
            Arguments.of(31, 100, 31),
            Arguments.of(31, -100, 31),
            Arguments.of(256, 8, 1),
            Arguments.of(256, 9, 256),
            Arguments.of(10, 2, 10)
        );
    }

    @ParameterizedTest
    @DisplayName("Do cyclic bit left shift in a number")
    @MethodSource("provideParametersForLeftShift")
    public void testRotateLeft(int inputNumber, int inputShift, int expectedResult) {
        final int shiftedNumber = CyclicBitShifter.rotateLeft(inputNumber, inputShift);
        assertThat(shiftedNumber)
                .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideParametersForLeftShift() {
        return Stream.of(
                Arguments.of(16, 1, 1),
                Arguments.of(17, 2, 6),
                Arguments.of(31, 100, 31),
                Arguments.of(64, 6, 32),
                Arguments.of(6, 1, 5)
        );
    }
}
