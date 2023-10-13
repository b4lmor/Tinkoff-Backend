package edu.hw2.task1;

public record Exponent(
    Expr e1,
    Expr e2
) implements Expr {
    public Exponent(Expr neg1, double neg2) {
        this(neg1, new Constant(neg2));
    }

    public Exponent(double neg1, Expr neg2) {
        this(new Constant(neg1), neg2);
    }

    public Exponent(double neg1, double neg2) {
        this(new Constant(neg1), new Constant(neg2));
    }

    @Override
    public double evaluate() {
        return Math.pow(e1.evaluate(), e2.evaluate());
    }

    @Override
    public String toString() {
        return "= " + e1.evaluate() + "^" + e2.evaluate();
    }
}
