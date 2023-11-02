package edu.project2.solver;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.entity.SolverName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    private static Stream<Arguments> provideSolvers() {
        return Stream.of(
            Arguments.of(SolverName.BFS),
            Arguments.of(SolverName.DFS),
            Arguments.of(SolverName.WAVE)
        );
    }

    @ParameterizedTest
    @DisplayName("Test recursive maze solver")
    @MethodSource("provideSolvers")
    public void testRecursiveSolver(SolverName solverName) {

        Maze maze = new Maze(
            5,
            5,
            new Cell[][] {
                {Cell.WALL, Cell.WALL,    Cell.WALL,    Cell.WALL,    Cell.WALL},
                {Cell.WALL, Cell.PASSAGE, Cell.PASSAGE, Cell.WALL,    Cell.WALL},
                {Cell.WALL, Cell.WALL,    Cell.PASSAGE, Cell.WALL,    Cell.WALL},
                {Cell.WALL, Cell.WALL,    Cell.PASSAGE, Cell.PASSAGE, Cell.WALL},
                {Cell.WALL, Cell.WALL,    Cell.WALL,    Cell.WALL,    Cell.WALL}
            }
        );

        Set<Coordinate> expectedPath = Set.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(3, 2),
            new Coordinate(3, 3)
        );

        Set<Coordinate> path = (new MazeSolverFactory())
            .getMazeSolver(
                solverName
            )
            .findPath(
                maze,
                new Coordinate(
                    1,
                    1
                ),
                new Coordinate(
                    5 - 2,
                    5 - 2
                )
            );

        assertEquals(path, expectedPath);
    }
}
