package edu.hw3.task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumberConverterTest {

    @ParameterizedTest
    @DisplayName("test Number Converter")
    @MethodSource("provideValues")
    public void testArabicToRoman(int input, String expectedResult) {
        String result = NumberConverter.arabicToRoman(input);
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI")
        );
    }
}
