package edu.hw2.task3;

import edu.hw2.task3.connection.IConnection;
import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.IConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final IConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(IConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try (IConnection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception ex) {
                LOGGER.info("Connection failed! (" + ex.getMessage() + ")");
            }
        }
        throw new ConnectionException("Can't set connection! Number of attempts exceeded.");
    }
}
