package edu.project2.renderer;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.entity.SolverName;
import edu.project2.renderer.impl.MazeRendererDefault;
import edu.project2.solver.MazeSolverFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RendererTest {

    private static Stream<Arguments> provideSolvers() {
        return Stream.of(
            Arguments.of(SolverName.RECURSIVE)
        );
    }

    @ParameterizedTest
    @DisplayName("Test maze renderer 'default'")
    @MethodSource("provideSolvers")
    public void testDefaultRenderer(SolverName solverName) {
        Maze maze = new Maze(
            5,
            5,
            new Cell[][] {
                {Cell.WALL, Cell.WALL,    Cell.WALL,    Cell.WALL,    Cell.WALL},
                {Cell.WALL, Cell.PASSAGE, Cell.PASSAGE, Cell.WALL,    Cell.WALL},
                {Cell.WALL, Cell.WALL,    Cell.PASSAGE, Cell.WALL,    Cell.WALL},
                {Cell.WALL, Cell.PASSAGE, Cell.PASSAGE, Cell.PASSAGE, Cell.WALL},
                {Cell.WALL, Cell.WALL,    Cell.WALL,    Cell.WALL,    Cell.WALL}
            }
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

        MazeRenderer renderer = new MazeRendererDefault();

        String mazeToPrint = renderer.render(maze);
        String solvedMazeToPrint = renderer.render(maze, path);

        String expectedMazeToPrint = "\n" + "\u001B[47m" + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                          + "\u001B[47m" + "   " + "\u001B[0;97m" + "   " + "\u001B[0;97m" + "   " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                          + "\u001B[47m" + "   " + "\u001B[47m"   + "   " + "\u001B[0;97m" + "   " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                          + "\u001B[47m" + "   " + "\u001B[0;97m" + "   " + "\u001B[0;97m" + "   " + "\u001B[0;97m" + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                          + "\u001B[47m" + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n";

        String expectedSolvedMazeToPrint = "\n" + "\u001B[47m" + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                                + "\u001B[47m" + "   " + "\u001B[0;31m" + " ∙ " + "\u001B[0;31m" + " ∙ " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                                + "\u001B[47m" + "   " + "\u001B[47m"   + "   " + "\u001B[0;31m" + " ∙ " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                                + "\u001B[47m" + "   " + "\u001B[0;97m" + "   " + "\u001B[0;31m" + " ∙ " + "\u001B[0;31m" + " ∙ " + "\u001B[47m" + "   " + "\u001B[0m" + "\n"
                                                + "\u001B[47m" + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m"   + "   " + "\u001B[47m" + "   " + "\u001B[0m" + "\n";

        assertEquals(mazeToPrint, expectedMazeToPrint);
        assertEquals(solvedMazeToPrint, expectedSolvedMazeToPrint);
    }
}
