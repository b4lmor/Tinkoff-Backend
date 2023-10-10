package edu.hw1.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NestCheckerTest {

    @ParameterizedTest
    @DisplayName("Check the possibility of nesting for VALID arrays")
    @MethodSource("provideValidParameters")
    public void testValidIsNestable(int[] array1, int[] array2, boolean expectedResult) {
        final boolean isNestable = NestChecker.isNestable(array1, array2);
        assertThat(isNestable)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4}, new int[]{0, 6}, true),
            Arguments.of(new int[]{3, 1}, new int[]{4, 0}, true),
            Arguments.of(new int[]{9, 9, 8}, new int[]{8, 9}, false),
            Arguments.of(new int[]{1, 2, 3, 4}, new int[]{2, 3}, false)
        );
    }

    @ParameterizedTest
    @DisplayName("Check the possibility of nesting for INVALID arrays")
    @MethodSource("provideInvalidParameters")
    public void testInvalidIsNestable(int[] array1, int[] array2) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NestChecker.isNestable(array1, array2);
        });
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
            Arguments.of(new int[]{1}, new int[]{0, 5}),
            Arguments.of(new int[]{}, new int[]{0, 6, 9}),
            Arguments.of(new int[]{}, new int[]{}),
            Arguments.of(null, new int[]{})
        );
    }
}
