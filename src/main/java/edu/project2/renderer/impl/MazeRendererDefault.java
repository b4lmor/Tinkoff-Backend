package edu.project2.renderer.impl;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.renderer.MazeRenderer;
import java.util.Set;

public class MazeRendererDefault implements MazeRenderer {
    private static final String CELL = "   ";
    private static final String PATH = " ∙ ";
    private static final String WALL_COLOR = "\u001B[47m";
    private static final String PASSAGE_COLOR = "\u001B[0;97m";
    private static final String PATH_COLOR = "\u001B[0;31m";
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public String render(Maze maze) {
        StringBuilder mazeViewBuilder = new StringBuilder(LINE_SEPARATOR);

        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                mazeViewBuilder
                    .append(
                        grid[i][j].isWall()
                            ? WALL_COLOR
                            : PASSAGE_COLOR
                    )
                    .append(CELL);
            }

            mazeViewBuilder
                .append(RESET_COLOR)
                .append(LINE_SEPARATOR);
        }

        return mazeViewBuilder.toString();
    }

    @Override
    public String render(Maze maze, Set<Coordinate> path) {
        StringBuilder mazeViewBuilder = new StringBuilder(LINE_SEPARATOR);

        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {

                if (path.contains(Coordinate.of(i, j))) {
                    mazeViewBuilder
                        .append(PATH_COLOR)
                        .append(PATH);

                } else {
                    mazeViewBuilder
                        .append(
                            grid[i][j].isWall()
                                ? WALL_COLOR
                                : PASSAGE_COLOR
                        )
                        .append(CELL);
                }
            }

            mazeViewBuilder
                .append(RESET_COLOR)
                .append(LINE_SEPARATOR);
        }

        return mazeViewBuilder.toString();
    }
}
