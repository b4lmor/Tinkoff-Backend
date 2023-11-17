package edu.project3.logparser.metrics.impl;

import edu.project3.entity.NginxData;
import edu.project3.logparser.metrics.Metric;
import edu.project3.utils.CollectionUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IpAddressMetric implements Metric {

    private static final String METRIC_NAME = "Популярные IP-адреса пользователей";
    private static final String FIRST_COLUMN_NAME = "IP-адрес";
    private static final String SECOND_COLUMN_NAME = "Количество запросов";

    private final Map<String, Integer> ipAddressFrequencyDict = new HashMap<>();

    @Override
    public Map<String, List<String>> getMetricData() {
        return CollectionUtils.<String>getTop3MaxValues(ipAddressFrequencyDict);
    }

    @Override
    public void applyData(NginxData nginxData) {
        ipAddressFrequencyDict.merge(nginxData.ipAddress(), 1, Integer::sum);
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
