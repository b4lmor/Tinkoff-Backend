package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExprTest {
    @Test
    @DisplayName("Evaluate expression from task example")
    void testTaskExample() {
        var two = new Constant(2); // 2
        var four = new Constant(4); // 4
        var negOne = new Negate(new Constant(1)); // -1
        var sumTwoFour = new Addition(two, four); // 6
        var mult = new Multiplication(sumTwoFour, negOne); // -6
        var exp = new Exponent(mult, 2); // 36
        var res = new Addition(exp, new Constant(1)); // 37

        var resEval = res.evaluate();

        assertThat(resEval)
            .isEqualTo(37);
    }

    @Test
    @DisplayName("Evaluate expression my test #1")
    void testMyExpression1() {
        var two = new Constant(2); // 2
        var negTwo = new Negate(two); // -2
        var negNegTwo = new Negate(negTwo); // 2
        var res = new Exponent(negTwo, negNegTwo); // 4

        var resEval = res.evaluate();

        assertThat(resEval)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("Evaluate expression my test #2")
    void testMyExpression2() {
        var zero = new Constant(0); // 0
        var negZero = new Negate(0); // 0
        var res = new Exponent(zero, negZero); // 0^0 in Java == 1

        var resEval = res.evaluate();

        assertThat(resEval)
            .isEqualTo(1);
    }
}
