package edu.project2.entity;


public enum Cell {
    WALL,
    PASSAGE;

    public boolean isClear(Cell cell) {
        return cell == PASSAGE;
    }
}

