package edu.project4.transformation.fractalflamevariations;

import edu.project4.transformation.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Popcorn implements Transformation {

  public double c;
  private double f;

  private static final int POPCORN_VALUE = 3;

  public Popcorn(double c, double f) {
    this.c = c;
    this.f = f;
  }

  @Override
  public Vector2D apply(Vector2D t) {
    return new Vector2D(t.getX() + c * FastMath.sin(FastMath.tan(POPCORN_VALUE * t.getY())),
        t.getY() + f * FastMath.sin(FastMath.tan(POPCORN_VALUE * t.getX())));
  }

}
