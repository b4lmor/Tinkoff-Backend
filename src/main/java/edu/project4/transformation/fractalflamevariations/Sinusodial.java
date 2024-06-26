package edu.project4.transformation.fractalflamevariations;


import edu.project4.transformation.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Sinusodial implements Transformation {

  @Override
  public Vector2D apply(Vector2D p) {
    return new Vector2D(FastMath.sin(p.getX()), FastMath.sin(p.getY()));
  }

}
