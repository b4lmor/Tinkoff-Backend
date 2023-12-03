package edu.project4.transformation.fractalflamevariations;


import edu.project4.transformation.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Horseshoe implements Transformation {

  @Override
  public Vector2D apply(Vector2D p) {
    double r = p.getNorm();
    return new Vector2D((p.getX() - p.getY()) / (p.getX() + p.getY()) / r,
        2.0 * p.getX() * p.getY() / r);
  }

}
