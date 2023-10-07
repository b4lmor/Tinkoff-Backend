package edu.hw1.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpecificPalindromeTest {

    @ParameterizedTest
    @DisplayName("Determine whether a number is a palindrome-descendant")
    @MethodSource("provideParameters")
    public void testIsPalindromeDescendant(int inputNumber, boolean expectedResult) {
        final boolean isPalindromeDescendant = SpecificPalindrome.isPalindromeDescendant(inputNumber);
        assertThat(isPalindromeDescendant)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(11211230, true),
            Arguments.of(13001120, true),
            Arguments.of(23336014, true),
            Arguments.of(11, true),
            Arguments.of(313, true),
            Arguments.of(51715, true),
            Arguments.of(123312, true),
            Arguments.of(-123312, true),
            Arguments.of(123, false),
            Arguments.of(1234, false),
            Arguments.of(654321, false),
            Arguments.of(0, false)
        );
    }
}
