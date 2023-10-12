package edu.hw2.task3.connection.impl;

import edu.hw2.task3.connection.IConnection;
import edu.hw2.task3.exception.ConnectionException;

public class FaultyConnection implements IConnection {

    @Override
    public void execute(String command) {
        LOGGER.info("Executing '" + command + "'...");
        LOGGER.info("Connecting...");
        LOGGER.info("Failed!");
        throw new ConnectionException();
    }

}
