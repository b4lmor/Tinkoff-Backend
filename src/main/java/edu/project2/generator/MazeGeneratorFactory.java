package edu.project2.generator;

import edu.project2.entity.GeneratorName;
import edu.project2.generator.impl.PrimMazeGenerator;

public class MazeGeneratorFactory {

    public MazeGenerator getMazeGenerator(GeneratorName generator) {
        return switch (generator) {
            case PRIM -> new PrimMazeGenerator();

        };
    }
}
