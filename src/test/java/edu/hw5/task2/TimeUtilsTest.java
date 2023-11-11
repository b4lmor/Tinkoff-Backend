package edu.hw5.task2;

import edu.hw5.task1.TimeManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeUtilsTest {

    private static Stream<Arguments> provideValuesForFindAllFridaysThe13th() {
        return Stream.of(
            Arguments.of(
                2024,
                List.of(
                    LocalDate.parse("2024-09-13"),
                    LocalDate.parse("2024-12-13")
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("test Time Utils")
    @MethodSource("provideValuesForFindAllFridaysThe13th")
    public void testFindAllFridaysThe13th(int input, List<LocalDate> expectedResult) {
        List<LocalDate> result = TimeUtils.findAllFridaysThe13th(input);

        assertEquals(
            result,
            expectedResult
        );
    }

    private static Stream<Arguments> provideValuesForFindNextFridaysThe13th() {
        return Stream.of(
            Arguments.of(
                LocalDate.parse("2024-09-14"),
                LocalDate.parse("2024-12-13")
            )
        );
    }

    @ParameterizedTest
    @DisplayName("test Time Utils")
    @MethodSource("provideValuesForFindNextFridaysThe13th")
    public void testFindNextFridaysThe13th(LocalDate input, LocalDate expectedResult) {
        LocalDate result = TimeUtils.findNextFridayThe13th(input);

        assertEquals(
            expectedResult,
            result
        );
    }
}
