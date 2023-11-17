package edu.project3.printer.impl;

import edu.project3.logparser.metrics.Metric;
import edu.project3.printer.LogPrinter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MarkdownLogPrinter implements LogPrinter {

    private static final String METRIC_HEADER_MARK = "##";

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String WRITE_ERROR_MSG = "Can't write to a file: ";
    private static final String CREATE_ERROR_MSG = "Can't create a file: ";
    private static final String CLOSE_ERROR_MSG = "Can't close a file writer: ";

    private final String filePath;
    private FileWriter fileWriter = null;

    public MarkdownLogPrinter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void printMetric(Metric metric) {
        if (!createFileIfNotExists() || fileWriter == null) {
            return;
        }

        try {
            fileWriter.write(makeHeader(metric.getMetricName()));
            fileWriter.write(metricToMarkdownTable(metric));

        } catch (IOException e) {
            LOGGER.error(WRITE_ERROR_MSG + e.getLocalizedMessage());
        }

    }

    @Override
    public void open() {
        try {
            this.fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            LOGGER.error(WRITE_ERROR_MSG + e.getLocalizedMessage());
        }
    }

    @Override
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.error(CLOSE_ERROR_MSG + e.getLocalizedMessage());
        }
    }

    private boolean createFileIfNotExists() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            return true;
        } catch (IOException e) {
            LOGGER.error(CREATE_ERROR_MSG + e.getLocalizedMessage());
            return false;
        }
    }

    private String makeHeader(String s) {
        return METRIC_HEADER_MARK + " " + s + '\n';
    }

    @SuppressWarnings("MultipleStringLiterals")
    private String metricToMarkdownTable(Metric metric) {
        var data = metric.getMetricData();
        StringBuilder result = new StringBuilder("| "
            + metric.getFirstColumnName()
            + " | "
            + metric.getSecondColumnName()
            + " |\n|-----|--------|\n"
        );

        for (var entry : data.entrySet()) {
            result.append("| ").append(entry.getKey()).append(" | ");
            for (String value : entry.getValue()) {
                result.append(value).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append(" |\n");
        }

        return result.toString();
    }

}
