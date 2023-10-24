package edu.project2;

import edu.project2.entity.Coordinate;
import edu.project2.entity.GeneratorName;
import edu.project2.entity.Maze;
import edu.project2.entity.SolverName;
import edu.project2.generator.MazeGeneratorFactory;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.impl.MazeRendererDefault;
import edu.project2.solver.MazeSolverFactory;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    private static final int HEIGHT = 21;
    private static final int WIDTH = 22;

    private Main() {
    }

    public static void main(String[] args) {

        Maze maze = (new MazeGeneratorFactory())
            .getMazeGenerator(
                GeneratorName.PRIM
            )
            .generate(
                HEIGHT,
                WIDTH
            );

        Set<Coordinate> path = (new MazeSolverFactory())
            .getMazeSolver(SolverName.RECURSIVE)
            .findPath(
                maze,
                new Coordinate(
                    1,
                    1
                ),
                new Coordinate(
                    HEIGHT - 2,
                    WIDTH - 2
                )
            );

        MazeRenderer renderer = new MazeRendererDefault();

        LOGGER.info(renderer.render(maze, path));
    }
}
