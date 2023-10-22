package edu.hw3.task6.market.impl;

import edu.hw3.task6.entity.Stock;
import edu.hw3.task6.market.StockMarket;
import edu.hw3.task6.repository.impl.StockRepository;

public class LondonStockExchange implements StockMarket {

    private final StockRepository stockRepository = new StockRepository();

    @Override
    public void add(Stock stock) {
        stockRepository.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockRepository.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockRepository.pickMax();
    }
}
