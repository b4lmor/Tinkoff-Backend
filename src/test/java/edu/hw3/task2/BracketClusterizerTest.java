package edu.hw3.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BracketClusterizerTest {

    @ParameterizedTest
    @DisplayName("test Atbash cipher (Valid)")
    @MethodSource("provideValidValues")
    public void testAtbashCipherValid(String input, List<String> expectedResult) {
        List<String> result = BracketClusterizer.clusterize(input);

        assertEquals(
                result.size(),
                expectedResult.size()
        );

        for (int i = 0; i < result.size(); i++) {
            assertEquals(
                    result.get(i),
                    expectedResult.get(i)
            );
        }
    }

    private static Stream<Arguments> provideValidValues() {
        return Stream.of(
            Arguments.of(
                    "()()()",
                    List.of("()", "()", "()")
            ),
            Arguments.of(
                    "((()))",
                    List.of("((()))")
            ),
            Arguments.of(
                    "((()))(())()()(()())",
                    List.of("((()))", "(())", "()", "()", "(()())")
            ),
            Arguments.of(
                    "((())())(()(()()))",
                    List.of("((())())", "(()(()()))")
            )
        );
    }

    @ParameterizedTest
    @DisplayName("test Atbash cipher (Invalid)")
    @MethodSource("provideInvalidValues")
    public void testAtbashCipherInvalid(String input) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BracketClusterizer.clusterize(input);
        });

    }

    private static Stream<Arguments> provideInvalidValues() {
        return Stream.of(
                Arguments.of("(()))))"),
                Arguments.of("(((((((())"),
                Arguments.of("(a)(b)(c)")

        );
    }

}
