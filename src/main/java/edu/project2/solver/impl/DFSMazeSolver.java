package edu.project2.solver.impl;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.solver.MazeSolver;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DFSMazeSolver implements MazeSolver {
    public Set<Coordinate> findPath(Maze maze, Coordinate start, Coordinate end) {

        Set<Coordinate> visited = new HashSet<>();
        visited.add(start);

        Set<Coordinate> path = dfs(maze, start, end, visited);
        return path == null ? Collections.emptySet() : path;
    }

    private Set<Coordinate> dfs(
        Maze maze,
        Coordinate current,
        Coordinate end,
        Set<Coordinate> visited) {

        if (current.equals(end)) {
            Set<Coordinate> path = new HashSet<>();
            path.add(current);
            return path;
        }

        for (Coordinate neighbor : maze.getNeighbors(current)) {

            if (!visited.contains(neighbor)) {

                visited.add(neighbor);
                Set<Coordinate> path = dfs(maze, neighbor, end, visited);

                if (path != null) {
                    path.add(current);
                    return path;
                }

            }
        }

        return null;
    }
}
