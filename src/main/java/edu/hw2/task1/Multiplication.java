package edu.hw2.task1;

public record Multiplication(
    Expr m1,
    Expr m2
) implements Expr {
    public Multiplication(Expr neg1, double neg2) {
        this(neg1, new Constant(neg2));
    }

    public Multiplication(double neg1, Expr neg2) {
        this(new Constant(neg1), neg2);
    }

    public Multiplication(double neg1, double neg2) {
        this(new Constant(neg1), new Constant(neg2));
    }

    @Override
    public double evaluate() {
        return m1.evaluate() * m2.evaluate();
    }

}
