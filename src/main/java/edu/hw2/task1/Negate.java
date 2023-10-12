package edu.hw2.task1;

public record Negate(
    Expr n1
) implements Expr {

    public Negate(double neg) {
        this(new Constant(neg));
    }

    @Override
    public double evaluate() {
        return -(n1.evaluate());
    }

    @Override
    public String toString() {
        return String.valueOf(this.evaluate());
    }
}
