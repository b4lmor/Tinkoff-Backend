package edu.hw3.task6.entity;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public record Stock(
    @NotNull String name,
    @NotNull BigInteger price,
    @NotNull UUID id,
    double dividendYield

) implements Comparable<Stock> {

    public Stock(String name, BigInteger price, double dividendYield) {
        this(name,
            price,
            UUID.randomUUID(),
            dividendYield
        );
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return price.compareTo(o.price());
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Stock cmpObj)) {
            return false;
        }

        return name.equals(cmpObj.name)
            && price.equals(cmpObj.price)
            && id.equals(cmpObj.id)
            && (dividendYield == cmpObj.dividendYield);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, id, dividendYield);
    }
}
