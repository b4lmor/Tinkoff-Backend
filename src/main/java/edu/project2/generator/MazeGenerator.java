package edu.project2.generator;

import edu.project2.entity.Maze;

public interface MazeGenerator {
    int MIN_HEIGHT = 2;
    int MIN_WIDTH = 2;

    Maze generate(int height, int width);
}
