package edu.project3.logparser.metrics;

import edu.project3.entity.Configuration;
import edu.project3.logparser.metrics.impl.ActiveHoursMetric;
import edu.project3.logparser.metrics.impl.GeneralMetric;
import edu.project3.logparser.metrics.impl.IpAddressMetric;
import edu.project3.logparser.metrics.impl.ResourcesMetric;
import edu.project3.logparser.metrics.impl.ResponseCodeMetric;
import java.util.EnumMap;
import java.util.List;

public class MetricFabric {

    private final EnumMap<MetricNames, Metric> metrics
        = new EnumMap<>(MetricNames.class);

    public MetricFabric(Configuration configuration) {
        metrics.put(
            MetricNames.GENERAL_METRIC,
            new GeneralMetric(configuration)
        );
        metrics.put(
            MetricNames.RESOURCES_METRIC,
            new ResourcesMetric()
        );
        metrics.put(
            MetricNames.RESPONSE_CODE_METRIC,
            new ResponseCodeMetric()
        );
        metrics.put(
            MetricNames.IP_ADDRESS_METRIC,
            new IpAddressMetric()
        );
        metrics.put(
            MetricNames.MOST_ACTIVE_HOURS_METRIC,
            new ActiveHoursMetric()
        );
    }

    public List<Metric> getMetrics() {
        return metrics.values().stream().toList();
    }

    public enum MetricNames {
        GENERAL_METRIC,
        RESOURCES_METRIC,
        RESPONSE_CODE_METRIC,
        IP_ADDRESS_METRIC,
        MOST_ACTIVE_HOURS_METRIC,
    }
}

