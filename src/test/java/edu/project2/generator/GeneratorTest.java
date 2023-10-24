package edu.project2.generator;

import edu.project2.entity.Cell;
import edu.project2.entity.GeneratorName;
import edu.project2.entity.Maze;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project2.entity.Cell.PASSAGE;
import static edu.project2.entity.Cell.WALL;
import static edu.project2.entity.GeneratorName.PRIM;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {

    private static Stream<Arguments> provideSolverAndGenerator() {
        return Stream.of(
            Arguments.of(PRIM)
        );
    }

    @ParameterizedTest
    @DisplayName("Test Maze Generator 2x2")
    @MethodSource("provideSolverAndGenerator")
    public void testMazeGenerator2x2(GeneratorName generatorName) {

        Maze maze = (new MazeGeneratorFactory())
            .getMazeGenerator(
                generatorName
            )
            .generate(
                2,
                2
            );

        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(
                    grid[i][j],
                    WALL
                );
            }
        }
    }

    @ParameterizedTest
    @DisplayName("Test Maze Generator 2x2")
    @MethodSource("provideSolverAndGenerator")
    public void testMazeGenerator3x3(GeneratorName generatorName) {

        Maze maze = (new MazeGeneratorFactory())
            .getMazeGenerator(
                generatorName
            )
            .generate(
                3,
                3
            );

        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(
                    grid[i][j],
                    (i == 1 && j == 1)
                        ? PASSAGE
                        : WALL
                );
            }
        }
    }

    @ParameterizedTest
    @DisplayName("Test Maze Generator 2x2")
    @MethodSource("provideSolverAndGenerator")
    public void testMazeGeneratorNxM(GeneratorName generatorName) {

        int N = 13;
        int M = 45;

        Assertions.assertDoesNotThrow(() -> {
            Maze maze = (new MazeGeneratorFactory())
                .getMazeGenerator(
                    generatorName
                )
                .generate(
                    N,
                    M
                );
        });



    }
}
