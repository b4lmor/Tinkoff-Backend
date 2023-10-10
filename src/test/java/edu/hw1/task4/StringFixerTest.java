package edu.hw1.task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringFixerTest {

    @ParameterizedTest
    @DisplayName("Fix broken string by swapping adjacent letters for VALID strings")
    @MethodSource("provideValidParameters")
    public void testValidFixString(String brokenString, String expectedResult) {
        final String fixedString = StringFixer.fixString(brokenString);
        assertThat(fixedString)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
            Arguments.of("123456", "214365"),
            Arguments.of("hTsii  s aimex dpus rtni.g", "This is a mixed up string."),
            Arguments.of("badce", "abcde"),
            Arguments.of("оПомигети псаривьтс ртко!и", "Помогите исправить строки!"),
            Arguments.of("123456", "214365"),
            Arguments.of("", ""),
            Arguments.of("a", "a"),
            Arguments.of("ab", "ba"),
            Arguments.of("abc", "bac")
        );
    }

    @ParameterizedTest
    @DisplayName("Fix broken string by swapping adjacent letters for INVALID string")
    @MethodSource("provideInvalidParameters")
    public void testInvalidFixString(String brokenString) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StringFixer.fixString(brokenString);
        });
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
            Arguments.of((Object) null)
            );
    }
}
