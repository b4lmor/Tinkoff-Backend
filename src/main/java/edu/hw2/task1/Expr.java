package edu.hw2.task1;

public sealed interface Expr permits Addition, Exponent, Multiplication, Negate, Constant {
     double evaluate();

}
