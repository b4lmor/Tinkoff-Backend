package edu.project4.transformation.fractalflamevariations;

import edu.project4.transformation.Transformation;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Chaotic implements Transformation {

  private final RealDistribution distribution;

  private static final double MEAN = 0.0;
  private static final double SEED = 0.1;

  public Chaotic() {
    this(new NormalDistribution(MEAN, SEED));
  }

  public Chaotic(RealDistribution distribution) {
    this.distribution = distribution;
  }

  @Override
  public Vector2D apply(Vector2D t) {
    return new Vector2D(t.getX() + distribution.sample(),
        t.getY() + distribution.sample());
  }

}
