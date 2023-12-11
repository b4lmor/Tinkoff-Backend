package edu.project4.transformation.fractalflamevariations;

import edu.project4.transformation.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Swirl implements Transformation {

  @Override
  public Vector2D apply(Vector2D t) {
    double rSquared = t.getNormSq();
    double cos = FastMath.cos(rSquared);
    double sin = FastMath.sin(rSquared);
    return new Vector2D(t.getX() * sin - t.getY() * cos,
        t.getX() * sin + t.getY() * cos);
  }

}
