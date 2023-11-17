package edu.project3.logparser.metrics;

import edu.project3.entity.NginxData;
import java.util.List;
import java.util.Map;

public interface Metric {
    default int getMetricFiledLength() {
        return 2;
    }

    Map<String, List<String>> getMetricData();

    void applyData(NginxData nginxData);

    void processData();

    String getMetricName();

    String getFirstColumnName();

    String getSecondColumnName();
}
