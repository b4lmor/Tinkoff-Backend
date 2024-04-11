package edu.project4.curves;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class LissajousCurve implements ParameterizedCurve {

  private static final double LISSAJOUS_VALUE = 2.0;

  private final double a;
  private final double b;
  private final double d;

  public LissajousCurve(double a, double b, double d) {
    this.a = a;
    this.b = b;
    this.d = d;
  }

  @Override
  public Vector2D apply(double t) {
    return new Vector2D(FastMath.sin(LISSAJOUS_VALUE * FastMath.PI * a * t + d),
            FastMath.cos(LISSAJOUS_VALUE * FastMath.PI * b * t));
  }

}
