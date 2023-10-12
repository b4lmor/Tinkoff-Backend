package edu.hw2.task1;

public record Constant(
    double c
) implements Expr {
    public Constant(Expr expr) {
        this(expr.evaluate());
    }

    @Override
    public double evaluate() {
        return c;
    }

}
