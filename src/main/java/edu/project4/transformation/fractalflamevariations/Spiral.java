package edu.project4.transformation.fractalflamevariations;


import edu.project4.transformation.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Spiral implements Transformation {

  @Override
  public Vector2D apply(Vector2D t) {
    double omega = FastMath.atan(t.getX() / t.getY());
    double r = t.getNorm();
    return new Vector2D(FastMath.cos(omega) + FastMath.sin(r),
        FastMath.sin(omega) - FastMath.cos(r)).scalarMultiply(1.0 / r);
  }

}
