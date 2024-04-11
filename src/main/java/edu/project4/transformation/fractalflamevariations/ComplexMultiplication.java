package edu.project4.transformation.fractalflamevariations;

import edu.project4.transformation.Transformation;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class ComplexMultiplication implements Transformation {

  private Complex a;

  public ComplexMultiplication(Complex a) {
    this.a = a;
  }

    public ComplexMultiplication(double real, double imaginary) {
        this.a = new Complex(real, imaginary);
    }

  @Override
  public Vector2D apply(Vector2D v) {
    Complex r = a.multiply(new Complex(v.getX(), v.getY()));
    return new Vector2D(r.getReal(), r.getImaginary());
  }

  public void setReal(double real) {
      a = new Complex(real, a.getImaginary());
  }

    public void setImaginary(double imaginary) {
        a = new Complex(a.getReal(), imaginary);
    }
}
