package edu.hw2.task3.manager.impl;

import edu.hw2.task3.connection.IConnection;
import edu.hw2.task3.connection.impl.FaultyConnection;
import edu.hw2.task3.connection.impl.StableConnection;
import edu.hw2.task3.manager.IConnectionManager;
import java.util.Random;

public class DefaultConnectionManager implements IConnectionManager {

    private final Random random = new Random();

    @Override
    public IConnection getConnection() {
        boolean isSomethingWrong = random.nextBoolean();
        if (isSomethingWrong) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
