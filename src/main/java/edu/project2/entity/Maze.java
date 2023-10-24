package edu.project2.entity;

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

}
