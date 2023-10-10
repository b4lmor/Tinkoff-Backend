package edu.hw1.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChessBoardTest {

    @ParameterizedTest
    @DisplayName("Check if any knight is under attack on the VALID board")
    @MethodSource("provideValidParameters")
    public void testValidKnightBoardCapture(int[][] board, boolean expectedResult) {
        final boolean isKnightUnderAttack = ChessBoard.knightBoardCapture(board);
        assertThat(isKnightUnderAttack)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
            Arguments.of(new int[][]{
                                {0, 0, 0, 1, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 1, 0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 1, 0, 1, 0},
                                {0, 1, 0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 1, 0, 0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 1, 0, 0, 0}
                    },
                    true),
                Arguments.of(new int[][]{
                                {1, 0, 1, 0, 1, 0, 1, 0},
                                {0, 1, 0, 1, 0, 1, 0, 1},
                                {0, 0, 0, 0, 1, 0, 1, 0},
                                {0, 0, 1, 0, 0, 1, 0, 1},
                                {1, 0, 0, 0, 1, 0, 1, 0},
                                {0, 0, 0, 0, 0, 1, 0, 1},
                                {1, 0, 0, 0, 1, 0, 1, 0},
                                {0, 0, 0, 1, 0, 1, 0, 1}
                        },
                        false)
        );
    }

    @ParameterizedTest
    @DisplayName("Check if any knight is under attack on the INVALID board")
    @MethodSource("provideInvalidParameters")
    public void testInvalidKnightBoardCapture(int[][] board) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ChessBoard.knightBoardCapture(board);
        });
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of((Object) new int[][]{
                                {1},
                                {0, 1},
                                {0, 0, 0},
                                {0, 0, 1, 0},
                                {1, 0, 0, 0, 1},
                                {0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 1, 0, 1},
                                {0, 0, 0, 1, 0, 1, 0, 1}
                        }),
                Arguments.of((Object) new int[][]{
                        {1, 0, 1, 0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 1, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0, 1, 0, 1}
                }),
                Arguments.of((Object) new int[][]{
                        {1, 0, 1, 0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1}
                }),
                Arguments.of((Object) null),
                Arguments.of((Object) new int[][]{null, null})
        );
    }
}
