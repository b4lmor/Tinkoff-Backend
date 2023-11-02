package edu.project2.solver.impl;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import edu.project2.solver.MazeSolver;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSMazeSolver implements MazeSolver {
    public Set<Coordinate> findPath(Maze maze, Coordinate start, Coordinate end) {
        Set<Coordinate> path = new HashSet<>();
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();

        queue.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {

            Coordinate current = queue.poll();

            if (current.equals(end)) {
                return buildPath(path, parentMap, current);
            }

            for (Coordinate neighbor : maze.getNeighbors(current)) {
                if (!parentMap.containsKey(neighbor)) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return path;
    }

    private Set<Coordinate> buildPath(
        Set<Coordinate> path,
        Map<Coordinate, Coordinate> parentMap,
        Coordinate current) {

        Coordinate newCurrent = current;
        while (newCurrent != null) {
            path.add(newCurrent);
            newCurrent = parentMap.get(newCurrent);
        }

        return path;
    }
}
