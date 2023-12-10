package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector {
    private final Map<String, double[]> data = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        data.put(metricName, values);
    }

    public Map<String, Double> stats() throws InterruptedException, ExecutionException {
        Map<String, Double> statsMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );

        for (var entry : data.entrySet()) {
            String metricName = entry.getKey();
            double[] values = entry.getValue();

            CompletableFuture<Double> sumFuture = CompletableFuture.supplyAsync(() ->
                calculateSum(values), executor);

            CompletableFuture<Double> averageFuture = CompletableFuture.supplyAsync(() ->
                calculateAverage(values), executor);

            CompletableFuture<Double> maxFuture = CompletableFuture.supplyAsync(() ->
                calculateMax(values), executor);

            CompletableFuture<Double> minFuture = CompletableFuture.supplyAsync(() ->
                calculateMin(values), executor);

            statsMap.put(metricName + "_sum", sumFuture.get());
            statsMap.put(metricName + "_avg", averageFuture.get());
            statsMap.put(metricName + "_max", maxFuture.get());
            statsMap.put(metricName + "_min", minFuture.get());
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        data.clear();

        return statsMap;
    }

    private double calculateSum(double[] values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    private double calculateAverage(double[] values) {
        double sum = calculateSum(values);
        return sum / values.length;
    }

    private double calculateMax(double[] values) {
        double max = Double.MIN_VALUE;
        for (double value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private double calculateMin(double[] values) {
        double min = Double.MAX_VALUE;
        for (double value : values) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

}
