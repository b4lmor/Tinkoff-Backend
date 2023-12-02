package edu.hw8.task3;

import edu.hw8.task3.hash.MD5;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hacker {

    private final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private String foundPassword;
    private boolean isFound;

    public String hackOneThread(String hashedPassword) {
        String password = passwordGenerator.generateNext();

        while (!MD5.getMd5(password)
            .equals(hashedPassword)) {
            password = passwordGenerator.generateNext();
        }

        return password;
    }

    public String hackMultiThreaded(String hashedPassword) {
        ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );

        while (!isFound) {
            executor.submit(() -> {
                String password = passwordGenerator.generateNext();
                if (MD5.getMd5(password)
                    .equals(hashedPassword)) {
                    foundPassword = password;
                    isFound = true;
                }
            });
        }

        executor.shutdownNow();
        return foundPassword;
    }
}
