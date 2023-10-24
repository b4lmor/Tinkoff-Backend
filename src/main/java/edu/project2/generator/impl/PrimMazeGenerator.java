package edu.project2.generator.impl;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Direction;
import edu.project2.entity.Maze;
import edu.project2.generator.MazeGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({
        "MissingSwitchDefault",
        "CyclomaticComplexity"
    })
public class PrimMazeGenerator implements MazeGenerator {

    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {

        if (height < MIN_HEIGHT || width < MIN_WIDTH) {
            throw new IllegalArgumentException();
        }

        Cell[][] grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                makeWall(i, j, grid);
            }
        }

        int x = random.nextInt(0, height / 2) * 2 + 1;
        int y = random.nextInt(0, width / 2) * 2 + 1;

        makePath(x, y, grid);

        List<Coordinate> toCheck = new ArrayList<>();

        if (y - 2 >= 0) {
            toCheck.add(new Coordinate(x, y - 2));
        }
        if (y + 2 < width) {
            toCheck.add(new Coordinate(x, y + 2));
        }
        if (x - 2 >= 0) {
            toCheck.add(new Coordinate(x - 2, y));
        }
        if (x + 2 < height) {
            toCheck.add(new Coordinate(x + 2, y));
        }

        while (!toCheck.isEmpty()) {
            int index = random.nextInt(toCheck.size());
            Coordinate point = toCheck.get(index);

            x = point.row();
            y = point.col();

            makePath(x, y, grid);
            toCheck.remove(index);

            List<Direction> directions =
                new ArrayList<>(
                    List.of(
                        Direction.values()
                    )
                );

            while (!directions.isEmpty()) {
                int dirIndex = random.nextInt(0, directions.size());

                switch (directions.get(dirIndex)) {
                    case DOWN -> {
                        if (y - 2 >= 0 && grid[x][y - 2] == Cell.PASSAGE) {
                            makePath(x, y - 1, grid);
                            directions.clear();
                        }
                    }
                    case UP -> {
                        if (y + 2 < width && grid[x][y + 2] == Cell.PASSAGE) {
                            makePath(x, y + 1, grid);
                            directions.clear();
                        }
                    }
                    case LEFT -> {
                        if (x - 2 >= 0 && grid[x - 2][y] == Cell.PASSAGE) {
                            makePath(x - 1, y, grid);
                            directions.clear();
                        }
                    }
                    case RIGHT -> {
                        if (x + 2 < height && grid[x + 2][y] == Cell.PASSAGE) {
                            makePath(x + 1, y, grid);
                            directions.clear();
                        }
                    }
                }

                if (directions.isEmpty()) {
                    break;
                }

                directions.remove(dirIndex);
            }

            if (y - 2 >= 0 && grid[x][y - 2] == Cell.WALL) {
                toCheck.add(new Coordinate(x, y - 2));
            }
            if (y + 2 < width && grid[x][y + 2] == Cell.WALL) {
                toCheck.add(new Coordinate(x, y + 2));
            }
            if (x - 2 >= 0 && grid[x - 2][y] == Cell.WALL) {
                toCheck.add(new Coordinate(x - 2, y));
            }
            if (x + 2 < height && grid[x + 2][y] == Cell.WALL) {
                toCheck.add(new Coordinate(x + 2, y));
            }
        }

        if (width % 2 == 0) {
            for (int i = 0; i < height; i++) {
                makeWall(i, width - 1, grid);
            }
        }

        if (height % 2 == 0) {
            for (int j = 0; j < width; j++) {
                makeWall(height - 1, j, grid);
            }
        }

        return new Maze(height, width, grid);
    }

    private void makePath(int row, int col, Cell[][] grid) {
        grid[row][col] = Cell.PASSAGE;
    }

    private void makeWall(int row, int col, Cell[][] grid) {
        grid[row][col] = Cell.WALL;
    }
}
