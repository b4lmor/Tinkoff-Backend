package edu.project2.entity;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int h, int w, Cell[][] cells) {
        height = h;
        width = w;
        grid = cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();
        List<Coordinate> neighbors = new ArrayList<>();

        if (isValidCoordinate(row - 1, col)) {
            neighbors.add(Coordinate.of(row - 1, col));
        }
        if (isValidCoordinate(row + 1, col)) {
            neighbors.add(Coordinate.of(row + 1, col));
        }
        if (isValidCoordinate(row, col - 1)) {
            neighbors.add(Coordinate.of(row, col - 1));
        }
        if (isValidCoordinate(row, col + 1)) {
            neighbors.add(Coordinate.of(row, col + 1));
        }

        return neighbors;
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width && grid[row][col].isPassage();
    }

}
