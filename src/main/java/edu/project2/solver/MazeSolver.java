package edu.project2.solver;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.Set;

public interface MazeSolver {
    Set<Coordinate> findPath(
        Maze maze,
        Coordinate start,
        Coordinate end
    );
}
