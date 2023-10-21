package edu.hw3.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequencyDictionaryTest {

    @ParameterizedTest
    @DisplayName("test String Frequency Dictionary")
    @MethodSource("provideStringValues")
    public void testStringFrequencyDictionary(List<String> input, Map<String, Integer> expectedResult) {
        Map<String, Integer> result = FrequencyDictionary.countFrequency(input);

        assertEquals(result, expectedResult);
    }

    private static Stream<Arguments> provideStringValues() {
        return Stream.of(
            Arguments.of(
                    List.of("a", "bb", "a", "bb"),
                    Map.ofEntries(
                        Map.entry("bb", 2),
                        Map.entry("a", 2)
            )),
            Arguments.of(
                    List.of("this", "and", "that", "and"),
                    Map.ofEntries(
                        Map.entry("this", 1),
                        Map.entry("and", 2),
                        Map.entry("that", 1)
                    )),
            Arguments.of(
                    List.of("код", "код", "код", "bug"),
                    Map.ofEntries(
                        Map.entry("код", 3),
                        Map.entry("bug", 1)
                    ))
        );
    }

    @ParameterizedTest
    @DisplayName("test Integer Frequency Dictionary")
    @MethodSource("provideIntegerValues")
    public void testIntegerFrequencyDictionary(List<Integer> input, Map<Integer, Integer> expectedResult) {
        Map<Integer, Integer> result = FrequencyDictionary.countFrequency(input);

        assertEquals(result, expectedResult);
    }

    private static Stream<Arguments> provideIntegerValues() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 1, 2, 2),
                        Map.ofEntries(
                                Map.entry(1, 2),
                                Map.entry(2, 2)
                        ))
        );
    }

}
