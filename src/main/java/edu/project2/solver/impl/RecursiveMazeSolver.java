package edu.project2.solver.impl;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.solver.MazeSolver;
import java.util.HashSet;
import java.util.Set;

public class RecursiveMazeSolver implements MazeSolver {
    @Override
    public Set<Coordinate> findPath(Maze maze, Coordinate start, Coordinate end) {

        int x1 = start.row();
        int y1 = start.col();
        int x2 = end.row();
        int y2 = end.col();

        int height = maze.getHeight();
        int wight = maze.getWidth();

        Set<Coordinate> path = new HashSet<>();
        boolean[][] visited = new boolean[height][wight];

        findPathRecursive(maze, x1, y1, x2, y2, visited, path);

        return path;
    }

    @SuppressWarnings("ReturnCount")
    private boolean findPathRecursive(
        Maze maze,
        int x,
        int y,
        int targetX,
        int targetY,
        boolean[][] visited,
        Set<Coordinate> path
    ) {

        if (x < 0 || x >= maze.getHeight() || y < 0 || y >= maze.getWidth()) {
            return false;
        }

        if (maze.getGrid()[x][y] == Cell.WALL || visited[x][y]) {
            return false;
        }

        if (x == targetX && y == targetY) {
            path.add(new Coordinate(x, y));
            return true;
        }

        visited[x][y] = true;

        if (findPathRecursive(maze, x + 1, y, targetX, targetY, visited, path)
            || findPathRecursive(maze, x - 1, y, targetX, targetY, visited, path)
            || findPathRecursive(maze, x, y + 1, targetX, targetY, visited, path)
            || findPathRecursive(maze, x, y - 1, targetX, targetY, visited, path)) {

            path.add(new Coordinate(x, y));

            return true;
        }

        return false;
    }
}
