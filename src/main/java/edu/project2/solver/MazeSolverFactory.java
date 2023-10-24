package edu.project2.solver;

import edu.project2.entity.SolverName;
import edu.project2.solver.impl.RecursiveMazeSolver;

public class MazeSolverFactory {

    public MazeSolver getMazeSolver(SolverName solver) {
        return switch (solver) {
            case RECURSIVE -> new RecursiveMazeSolver();

        };
    }
}
