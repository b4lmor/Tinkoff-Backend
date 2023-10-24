package edu.project2.renderer.impl;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.renderer.MazeRenderer;
import java.util.Set;
import static edu.project2.entity.Cell.WALL;

@SuppressWarnings("MultipleStringLiterals")
public class MazeRendererDefault implements MazeRenderer {
    private final String spriteWall = "\u001B[47m" + "   ";
    private final String spritePassage = "\u001B[0;97m" + "   ";
    private final String spritePath = "\u001B[0;31m" + " ∙ ";
    private final String resetColor = "\u001B[0m";

    @Override
    public String render(Maze maze) {
        StringBuilder mazeViewBuilder = new StringBuilder("\n");

        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                mazeViewBuilder.append(
                    grid[i][j] == WALL
                        ? spriteWall
                        : spritePassage
                );
            }

            mazeViewBuilder.append(resetColor + "\n");
        }

        return mazeViewBuilder.toString();
    }

    @Override
    public String render(Maze maze, Set<Coordinate> path) {
        StringBuilder mazeViewBuilder = new StringBuilder("\n");

        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {

                if (path.contains(new Coordinate(i, j))) {
                    mazeViewBuilder.append(spritePath);

                } else {
                    mazeViewBuilder.append(
                        grid[i][j] == WALL
                            ? spriteWall
                            : spritePassage
                    );
                }
            }

            mazeViewBuilder.append(resetColor + "\n");
        }

        return mazeViewBuilder.toString();
    }
}
