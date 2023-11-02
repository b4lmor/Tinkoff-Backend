package edu.project2.renderer;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.Set;

public interface MazeRenderer {

    String render(Maze maze);

    String render(Maze maze, Set<Coordinate> path);
}
