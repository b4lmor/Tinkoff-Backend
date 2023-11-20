package edu.project3.logparser;

import edu.project3.logparser.metrics.Metric;
import java.util.List;

public interface LogParser {

    List<Metric> parseLogData();
}
