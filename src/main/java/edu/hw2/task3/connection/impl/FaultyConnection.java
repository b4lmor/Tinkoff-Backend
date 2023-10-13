package edu.hw2.task3.connection.impl;

import edu.hw2.task3.connection.IConnection;
import edu.hw2.task3.exception.ConnectionException;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class FaultyConnection implements IConnection {
    @Override
    public void execute(String command) {
        LOGGER.info("Executing '" + command + "'...");
        LOGGER.info("Connecting...");

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        switch (dayOfWeek) {
            case SUNDAY, SATURDAY -> {
                LOGGER.info("Failed!");
                throw new ConnectionException("Looks like there are too many requests today!");
            }
            default -> {
                LOGGER.info("Connected!");
                LOGGER.info("Done!");
            }
        }
    }

}
