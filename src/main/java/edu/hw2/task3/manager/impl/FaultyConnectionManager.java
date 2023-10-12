package edu.hw2.task3.manager.impl;

import edu.hw2.task3.connection.IConnection;
import edu.hw2.task3.connection.impl.FaultyConnection;
import edu.hw2.task3.manager.IConnectionManager;

public class FaultyConnectionManager implements IConnectionManager {
    @Override
    public IConnection getConnection() {
        return new FaultyConnection();
    }
}
