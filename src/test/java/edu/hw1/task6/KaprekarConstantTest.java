package edu.hw1.task6;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KaprekarConstantTest {

    @ParameterizedTest
    @DisplayName("Count the steps required to achieve the Kaprekar's constant with VALID number")
    @MethodSource("provideValidParameters")
    public void testValidCountK(int inputNumber, int expectedResult) {
        final int steps = KaprekarConstant.countK(inputNumber);
        assertThat(steps)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
            Arguments.of(6621, 5),
            Arguments.of(6554, 4),
            Arguments.of(1234, 3)
        );
    }

    @ParameterizedTest
    @DisplayName("Count the steps required to achieve the Kaprekar's constant with INVALID number")
    @MethodSource("provideInvalidParameters")
    public void testInvalidCountK(int input_number) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            KaprekarConstant.countK(input_number);
        });
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
            Arguments.of(1000),
            Arguments.of(0),
            Arguments.of(-1000),
            Arguments.of(1000000)
        );
    }
}
