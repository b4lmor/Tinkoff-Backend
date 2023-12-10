package edu.hw9.task3;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestThreadedSolver {

    @Test
    public void testSolver() {

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

        Set<Coordinate> path = new ThreadedWaveSolver()
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

        assertEquals(expectedPath, path);
    }
}
