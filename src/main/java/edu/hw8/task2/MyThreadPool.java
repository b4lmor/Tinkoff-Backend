package edu.hw8.task2;

public interface MyThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);
}
