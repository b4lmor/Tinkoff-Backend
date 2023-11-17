package edu.project3.logparser.metrics.impl;

import edu.project3.entity.Configuration;
import edu.project3.entity.NginxData;
import edu.project3.logparser.metrics.Metric;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralMetric implements Metric {

    private static final String METRIC_NAME = "Общая информация";
    private static final String FIRST_COLUMN_NAME = "Метрика";
    private static final String SECOND_COLUMN_NAME = "Значение";

    private final List<String> logFileName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private long requestNumber = 0;
    private long avgResponseSize = 0;
    private long totalResponseSize = 0;

    public GeneralMetric(Configuration configuration) {
        this.logFileName = Arrays.stream(configuration.logPaths()).toList();
        this.startDate = configuration.startDate();
        this.endDate = configuration.endDate();
    }

    @Override
    public Map<String, List<String>> getMetricData() {

        Map<String, List<String>> data = new HashMap<>();

        for (var metricName : Metrics.values()) {
            String key = metricName.getName();
            List<String> value = switch (metricName) {
                case FILES -> logFileName;
                case START_DATA -> List.of(startDate == null
                    ? "-"
                    : startDate.toString());
                case END_DATA -> List.of(endDate == null
                    ? "-"
                    : endDate.toString());
                case REQUEST_NUMBER -> List.of(String.valueOf(requestNumber));
                case AVG_RESPONSE_SIZE -> List.of(String.valueOf(avgResponseSize));
            };

            data.put(key, value);
        }

        return data;
    }

    @Override
    public void applyData(NginxData nginxData) {
        requestNumber++;
        totalResponseSize += nginxData.bytesSent();
    }

    @Override
    public void processData() {
        avgResponseSize = requestNumber == 0
            ? 0
            : totalResponseSize / requestNumber;
    }

    @Override
    public String getMetricName() {
        return METRIC_NAME;
    }

    @Override
    public String getFirstColumnName() {
        return FIRST_COLUMN_NAME;
    }

    @Override
    public String getSecondColumnName() {
        return SECOND_COLUMN_NAME;
    }

    private enum Metrics {
        FILES("Файл(-ы)"),
        START_DATA("Начальная дата "),
        END_DATA("Конечная дата"),
        REQUEST_NUMBER("Количество запросов"),
        AVG_RESPONSE_SIZE("Средний размер ответа");

        private final String name;

        Metrics(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
