package edu.hw5.task1;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeManagerTest {

    private static Stream<Arguments> provideValidValues() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                "3h 40m"
            ),
            Arguments.of(
                List.of(
                    "2022-12-31, 23:00 - 2023-01-01, 01:00",
                    "2022-04-01, 22:00 - 2022-04-02, 06:00"
                ),
                "5h 0m"
            )
        );
    }

    @ParameterizedTest
    @DisplayName("test Time Manager (Valid)")
    @MethodSource("provideValidValues")
    public void testGetAvgValid(List<String> input, String expectedResult) {
        String result = TimeManager.getAvg(input);

        assertEquals(
            result,
            expectedResult
        );
    }

    private static Stream<Arguments> provideInvalidValues() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "aaa"
                ),
                IllegalArgumentException.class
            ),
            Arguments.of(
                List.of(
                    "4444-44-44, 44:44 - 2023-01-01, 01:00"
                ),
                DateTimeParseException.class
            )
        );
    }

    @ParameterizedTest
    @DisplayName("test Time Manager (Invalid)")
    @MethodSource("provideInvalidValues")
    public void testGetAvgInvalid(List<String> input, Class<Exception> expectedExceptionClass) {

        Assertions.assertThrows(expectedExceptionClass, () -> {
            TimeManager.getAvg(input);
        });
    }
}
