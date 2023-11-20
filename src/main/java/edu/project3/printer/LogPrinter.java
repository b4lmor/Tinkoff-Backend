package edu.project3.printer;

import edu.project3.logparser.metrics.Metric;

public interface LogPrinter {
    void printMetric(Metric metric);

    void close();

    void open();
}
