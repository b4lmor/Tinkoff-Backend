package edu.hw5.task3;

import edu.hw5.task1.TimeManager;
import java.time.LocalDate;
import java.util.stream.Stream;
import edu.hw5.task3.chain.PatternChain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {

    private static Stream<Arguments> provideValidValues() {
        return Stream.of(
            Arguments.of(
                "today",
                LocalDate.now()
            ),
            Arguments.of(
                "tomorrow",
                LocalDate.now().plusDays(1)
            ),
            Arguments.of(
                "yesterday",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "2020-10-10",
                LocalDate.of(2020, 10, 10)
            ),
            Arguments.of(
                "2020-12-2",
                LocalDate.of(2020, 12, 2)
            ),
            Arguments.of(
                "1/3/1976",
                LocalDate.of(1976, 3, 1)
            ),
            Arguments.of(
                "1/3/20",
                LocalDate.of(2020, 3, 1)
            ),
            Arguments.of(
                "1 day ago",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "2000 days ago",
                LocalDate.now().minusDays(2000)
            )

        );
    }

    @ParameterizedTest
    @DisplayName("test Date Parser (Valid)")
    @MethodSource("provideValidValues")
    public void testParserValid(String input, LocalDate expectedResult) {

        PatternChain chain = PatternChain.initialize();

        var result = chain.parsePattern(input);

        assertEquals(
            expectedResult,
            result.get()
        );
    }
}
