package edu.hw2.task3;

import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.impl.DefaultConnectionManager;
import edu.hw2.task3.manager.impl.FaultyConnectionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class CommandExecutorTest {
    @Test
    @DisplayName("Execute command with faulty connection manager.")
    void testFaultyConnectionManager() {
        var popularCommandExecutor = new PopularCommandExecutor(
            new FaultyConnectionManager(),
            10
        );

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        switch (dayOfWeek) {
            case SUNDAY, SATURDAY -> {
                Assertions.assertThrows(
                    ConnectionException.class,
                    popularCommandExecutor::updatePackages
                );
            }
            default -> {
            }
        }


    }

    @Test
    @DisplayName("Execute command with default connection manager. Unpredictable result, monitor via logger")
    void testDefaultConnectionManager() {
        var popularCommandExecutor = new PopularCommandExecutor(
            new DefaultConnectionManager(),
            10
        );

        popularCommandExecutor.updatePackages();
    }

}
