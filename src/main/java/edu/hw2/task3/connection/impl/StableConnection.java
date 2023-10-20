package edu.hw2.task3.connection.impl;

import edu.hw2.task3.connection.IConnection;

public class StableConnection implements IConnection {
    @Override
    public void execute(String command) {
        LOGGER.info("Executing '" + command + "'...");
        LOGGER.info("Connection...");
        LOGGER.info("Connected!");
        LOGGER.info("Done!");
    }

}
