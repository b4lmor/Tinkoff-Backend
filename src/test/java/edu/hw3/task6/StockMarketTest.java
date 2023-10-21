package edu.hw3.task6;

import edu.hw3.task6.entity.Stock;
import edu.hw3.task6.market.impl.LondonStockExchange;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMarketTest {

    private final Stock gazprom = new Stock(
        "Gazprom",
        BigInteger.valueOf(1000000000),
        0.1
    );

    private final Stock yandex = new Stock(
        "Yandex",
        BigInteger.valueOf(47500000),
        0.5
    );

    private final Stock valve = new Stock(
        "Valve",
        BigInteger.valueOf(950000000),
        0.2
    );

    private final Stock vk = new Stock(
        "VK",
        BigInteger.valueOf(404040404),
        0.05
    );

    private final Stock facebook = new Stock(
        "Facebook",
        BigInteger.valueOf(88888888),
        0.65
    );

    @Test
    @DisplayName("test Stock Market")
    public void testStockMarket() {
        List<Stock> stocks = List.of(
            yandex, valve, gazprom, vk, facebook
        );

        LondonStockExchange stockExchange = new LondonStockExchange();

        stocks.forEach(stockExchange::add);

        Stock mostValuableStock = stockExchange.mostValuableStock();

        assertEquals(
            mostValuableStock,
            gazprom
        );

        stockExchange.remove(gazprom);

        mostValuableStock = stockExchange.mostValuableStock();

        assertEquals(
            mostValuableStock,
            valve
        );

    }

}
