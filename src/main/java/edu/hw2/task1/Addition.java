package edu.hw2.task1;

public record Addition(
    Expr a1,
    Expr a2
) implements Expr {
    public Addition(Expr neg1, double neg2) {
        this(neg1, new Constant(neg2));
    }

    public Addition(double neg1, Expr neg2) {
        this(new Constant(neg1), neg2);
    }

    public Addition(double neg1, double neg2) {
        this(new Constant(neg1), new Constant(neg2));
    }

    @Override
    public double evaluate() {
        return a1.evaluate() + a2.evaluate();
    }

    @Override
    public String toString() {
        return "= " + a1.evaluate() + " + " + a2.evaluate();
    }
}
