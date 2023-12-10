package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Factorial {

    private Factorial() {
    }

    public static BigInteger count(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        return LongStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
