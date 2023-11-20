package edu.project3.logparser.metrics.impl;

import edu.project3.entity.NginxData;
import edu.project3.logparser.metrics.Metric;
import edu.project3.utils.CollectionUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveHoursMetric implements Metric {

    private static final String METRIC_NAME = "Самые активные часы для запросов";
    private static final String FIRST_COLUMN_NAME = "Время";
    private static final String SECOND_COLUMN_NAME = "Количество";

    private final Map<String, Integer> responseCodeFrequencyDict = new HashMap<>();

    @Override
    public Map<String, List<String>> getMetricData() {
        return CollectionUtils.<String>getTop3MaxValues(responseCodeFrequencyDict);
    }

    @Override
    public void applyData(NginxData nginxData) {
        responseCodeFrequencyDict.merge(
            nginxData.time().getHour() + "h",
            1,
            Integer::sum
        );
    }

    @Override
    public void processData() {
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

}
