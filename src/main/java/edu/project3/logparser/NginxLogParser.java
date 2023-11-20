package edu.project3.logparser;

import edu.project3.entity.Configuration;
import edu.project3.entity.HttpMethod;
import edu.project3.entity.NginxData;
import edu.project3.logparser.metrics.Metric;
import edu.project3.logparser.metrics.MetricFabric;
import edu.project3.utils.HttpUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class NginxLogParser implements LogParser {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILE_ERROR_MSG = "Can't read the file: ";
    private static final String CLOSE_FILE_ERROR_MSG = "Can't close the buffered reader: ";
    private static final String INVALID_LOG_ERROR_MSG = "Invalid log format";

    private static final DateTimeFormatter DATE_TIME_PATTERN
        = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
        .localizedBy(Locale.US);

    private static final String HTTP_URL_PATTERN = "https?://.*";
    private static final String NGINX_LOG_PATTERN
        = "^(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"(\\S+)\".*";

    private static final String TEMPORARY_LOG_FILE_PATH = "src/main/resources/logparser/tmp.txt";

    private static final int IP_ADDRESS_GROUP_NUMBER = 1;
    private static final int DATE_GROUP_NUMBER = 4;
    private static final int HTTP_METHOD_GROUP_NUMBER = 5;
    private static final int REQUEST_URI_GROUP_NUMBER = 6;
    private static final int HTTP_VERSION_GROUP_NUMBER = 7;
    private static final int STATUS_CODE_GROUP_NUMBER = 8;
    private static final int BYTE_SIZE_RESPONSE_GROUP_NUMBER = 9;

    private final Configuration configuration;

    private BufferedReader currentBufferedReader = null;
    private int inputLogIndex = 0;

    public NginxLogParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<Metric> parseLogData() {
        MetricFabric metricFabric = new MetricFabric(configuration);
        List<Metric> metrics = metricFabric.getMetrics();

        NginxData nextNginxData;
        while ((nextNginxData = readNextLog()) != null) {
            if (isLogDateInRange(nextNginxData)) {

                NginxData finalNextNginxData = nextNginxData;
                metrics.forEach(metric -> metric.applyData(finalNextNginxData));
            }
        }
        metrics.forEach(Metric::processData);

        try {
            if (currentBufferedReader != null) {
                currentBufferedReader.close();
            }
        } catch (IOException e) {
            LOGGER.error(CLOSE_FILE_ERROR_MSG + e);
        }

        return metrics;
    }

    private static NginxData parseNginxLog(@NotNull String logLine) {
        Pattern pattern = Pattern.compile(NGINX_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {
            return new NginxData(
                matcher.group(IP_ADDRESS_GROUP_NUMBER),
                LocalDateTime.parse(matcher.group(DATE_GROUP_NUMBER), DATE_TIME_PATTERN),
                HttpMethod.parse(matcher.group(HTTP_METHOD_GROUP_NUMBER)),
                matcher.group(REQUEST_URI_GROUP_NUMBER),
                matcher.group(HTTP_VERSION_GROUP_NUMBER),
                Integer.parseInt(matcher.group(STATUS_CODE_GROUP_NUMBER)),
                Integer.parseInt(matcher.group(BYTE_SIZE_RESPONSE_GROUP_NUMBER))
            );

        } else {
            LOGGER.error(INVALID_LOG_ERROR_MSG);
            throw new IllegalArgumentException();
        }
    }

    private NginxData readNextLog() {

        if (currentBufferedReader == null) {
            if (!openNextStream()) {
                return null;
            }
        }

        try {
            String logLine = currentBufferedReader.readLine();
            if (logLine == null) {
                logLine = currentBufferedReader.readLine();
                if (!openNextStream() || logLine == null) {
                    return null;
                }
            }

            return NginxLogParser.parseNginxLog(logLine);

        } catch (IOException e) {
            LOGGER.error(FILE_ERROR_MSG + e.getMessage());
            return null;
        }

    }

    private boolean openNextStream() {
        try {
            if (inputLogIndex >= configuration.logPaths().length) {
                return false;
            }
            String filePath = configuration.logPaths()[inputLogIndex];
            if (filePath.matches(HTTP_URL_PATTERN)) {
                HttpUtils.saveHttpRequestToFile(filePath, TEMPORARY_LOG_FILE_PATH);
                filePath = TEMPORARY_LOG_FILE_PATH;
            }
            currentBufferedReader
                = new BufferedReader(new FileReader(filePath));
            inputLogIndex++;
            return true;

        } catch (IOException | InterruptedException e) {
            LOGGER.error(FILE_ERROR_MSG + e.getMessage());
            return false;
        }
    }

    private boolean isLogDateInRange(NginxData nginxData) {
        boolean isBeforeStartDate
            = configuration.startDate() != null
            && nginxData.time().toLocalDate().isBefore(configuration.startDate());
        boolean isAfterEndDate
            = configuration.endDate() != null
            && nginxData.time().toLocalDate().isAfter(configuration.endDate());

        return !(isAfterEndDate || isBeforeStartDate);
    }

}
