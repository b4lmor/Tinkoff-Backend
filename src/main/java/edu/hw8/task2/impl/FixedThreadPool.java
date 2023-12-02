package edu.hw8.task2.impl;

import edu.hw8.task2.MyThreadPool;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements MyThreadPool {
    private final int numThreads;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;
    private volatile boolean isRunning = false;

    public FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new Thread[numThreads];
    }

    @Override
    public void start() {
        if (isRunning) {
            throw new IllegalStateException("Thread pool is already running");
        }

        isRunning = true;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new TaskWorker());
            threads[i].start();
        }

    }

    @Override
    public void execute(Runnable runnable) {
        if (!isRunning) {
            throw new IllegalStateException("Thread pool is not running");
        }

        taskQueue.offer(runnable);
    }

    @Override
    public void close() {

        boolean isFinished = false;
        while (!isFinished) {
            isFinished = taskQueue.isEmpty();
        }

        isRunning = false;
        Arrays.stream(threads).forEach(Thread::interrupt);
    }

    private class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    if (!taskQueue.isEmpty()) {
                        Runnable task = taskQueue.take();
                        task.run();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
