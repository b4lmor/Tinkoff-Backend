package edu.hw2.task1;

public record Negate(
    Expr n
) implements Expr {

    public Negate(double neg) {
        this(new Constant(neg));
    }

    @Override
    public double evaluate() {
        return -(n.evaluate());
    }

    @Override
    public String toString() {
        return "= -1 * " + n.evaluate();
    }
}
