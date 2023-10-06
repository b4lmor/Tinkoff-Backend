package edu.hw1.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VideoLengthTest {

    @ParameterizedTest
    @DisplayName("Parse video length from VALID 'mm:ss'")
    @MethodSource("provideValidParameters")
    public void testParseValidVideoLength(String rawVideoLength, int expectedResult) {
        final int parsedVideoLength = VideoLength.parseVideoLength(rawVideoLength);
        assertThat(parsedVideoLength)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
            Arguments.of("15:55", 15 * 60 + 55),
            Arguments.of("33:33", 33 * 60 + 33),
            Arguments.of("10:00", 10 * 60),
            Arguments.of("56:00", 56 * 60),
            Arguments.of("00:07", 7),
            Arguments.of("00:55", 55)
        );
    }

    @ParameterizedTest
    @DisplayName("Parse video length from INVALID 'mm:ss'")
    @MethodSource("provideInvalidParameters")
    public void testParseInvalidVideoLength(String rawVideoLength) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            VideoLength.parseVideoLength(rawVideoLength);
        });
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
            Arguments.of("65:55"),
            Arguments.of("773:33"),
            Arguments.of("10:008"),
            Arguments.of("6:00"),
            Arguments.of("00:99"),
            Arguments.of(":")
        );
    }
}
