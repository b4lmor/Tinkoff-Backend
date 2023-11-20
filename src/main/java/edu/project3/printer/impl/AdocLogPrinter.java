package edu.project3.printer.impl;

import edu.project3.logparser.metrics.Metric;
import edu.project3.printer.LogPrinter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdocLogPrinter implements LogPrinter {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String WRITE_ERROR_MSG = "Can't write to a file: ";
    private static final String CREATE_ERROR_MSG = "Can't create a file: ";
    private static final String CLOSE_ERROR_MSG = "Can't close a file writer: ";


    private final String filePath;
    private FileWriter fileWriter = null;

    public AdocLogPrinter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void printMetric(Metric metric) {
        if (!createFileIfNotExists() || fileWriter == null) {
            return;
        }

        try {
            fileWriter.write(makeHeader(metric.getMetricName()));
            fileWriter.write(metricToAdocTable(metric));

        } catch (IOException e) {
            LOGGER.error(WRITE_ERROR_MSG + e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    private String metricToAdocTable(Metric metric) {
        var data = metric.getMetricData();
        StringBuilder adocTable = new StringBuilder("|===\n");
        adocTable.append("|")
            .append(metric.getFirstColumnName())
            .append(" | ")
            .append(metric.getSecondColumnName())
            .append("\n");

        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            adocTable.append("|").append(entry.getKey()).append(" | ");
            List<String> values = entry.getValue();

            for (String value : values) {
                adocTable.append(value)
                    .append(" ");
            }

            adocTable.append("\n");
        }
        adocTable.append("|===\n\n");

        return adocTable.toString();
    }

    private String makeHeader(String metricName) {
        return "*" + metricName.toUpperCase() + "*\n";
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
}
