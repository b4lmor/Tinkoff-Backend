package edu.project4.chaosgame;

import edu.project4.entity.FractalImage;
import edu.project4.transformation.Transformation;
import java.awt.geom.Rectangle2D;
import java.util.List;

public interface ChaosGame {

    FractalImage iterate(
        List<Transformation> fractalTransformations,
        int samples,
        int iterationPerSample,
        int symmetry,
        Rectangle2D.Double drawArea,
        FractalImage canvas
    );
}
