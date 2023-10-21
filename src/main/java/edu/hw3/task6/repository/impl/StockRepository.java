package edu.hw3.task6.repository.impl;

import edu.hw3.task6.entity.Stock;
import edu.hw3.task6.repository.Repository;
import java.util.Collections;
import java.util.PriorityQueue;

public class StockRepository implements Repository<Stock> {

    private final PriorityQueue<Stock> data
        = new PriorityQueue<>(
            Collections.reverseOrder()
    );

    @Override
    public void add(Stock stock) {
        data.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        data.remove(stock);
    }

    @Override
    public Stock pickMax() {
        return data.isEmpty()
            ? null
            : data.peek();
    }
}
