package edu.project2.solver;

import edu.project2.entity.SolverName;
import edu.project2.solver.impl.BFSMazeSolver;
import edu.project2.solver.impl.DFSMazeSolver;
import edu.project2.solver.impl.WaveMazeSolver;

public class MazeSolverFactory {

    public MazeSolver getMazeSolver(SolverName solver) {
        return switch (solver) {
            case BFS -> new BFSMazeSolver();
            case DFS -> new DFSMazeSolver();
            case WAVE -> new WaveMazeSolver();

        };
    }
}
