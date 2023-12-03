package edu.project4.transformation.fractalflamevariations;

import edu.project4.transformation.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Heart implements Transformation {

  @Override
  public Vector2D apply(Vector2D t) {
    double omega = FastMath.atan(t.getX() / t.getY());
    double r = t.getNorm();

    double omegaR = omega * r;
    return new Vector2D(FastMath.sin(omegaR), -FastMath.cos(omegaR)).scalarMultiply(r);
  }

}
