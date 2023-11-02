package edu.project2.entity;


public enum Cell {
    WALL,
    PASSAGE;

    public boolean isPassage() {
        return this == PASSAGE;
    }

    public boolean isWall() {
        return this == WALL;
    }
}

