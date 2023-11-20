package edu.project3.parser;

import edu.project3.entity.Configuration;
import edu.project3.logparser.NginxLogParser;
import edu.project3.logparser.metrics.Metric;
import edu.project3.logparser.metrics.MetricFabric;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogParserTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final List<String> TEST_LOGS = List.of(
        "80.91.33.133 - - [17/May/2015:08:05:01 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)\"",
        "93.180.71.3 - - [17/May/2015:08:05:27 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "217.168.17.5 - - [17/May/2015:08:05:12 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 3316 \"-\" \"-\""
    );
    private static final String TEST_LOG_PATH = "src/main/resources/logparser/testlog.txt";

    private static final String[] LOG_PATHS = {
        TEST_LOG_PATH
    };
    private static final LocalDate START_DATE = LocalDate.of(2015, 5, 1);
    private static final LocalDate END_DATE = LocalDate.of(2015, 6, 30);
    private static final Configuration.Format FORMAT = Configuration.Format.MARKDOWN;


    @BeforeAll
    static void createTestLog() {
        File file = new File("src/main/resources/logparser/nginx_logs.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.error("Can't create a file : " + e);
            }
        }

        try {
            Path path = Paths.get(TEST_LOG_PATH);
            Files.write(path, TEST_LOGS, Charset.defaultCharset());

        } catch (IOException e) {
            LOGGER.error("Can't write to a file : " + e);
        }
    }

    @AfterAll
    static void deleteTestLogFile() {
        Path path = Paths.get(TEST_LOG_PATH);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            LOGGER.error("Can't delete a file : " + e);
        }
    }

    @Test
    void testNginxLogParser() {
        Configuration configuration = new Configuration(
            LOG_PATHS,
            START_DATE,
            END_DATE,
            FORMAT
        );

        NginxLogParser parser = new NginxLogParser(configuration);

        List<Metric> metrics = parser.parseLogData();

        assertEquals(
            MetricFabric.MetricNames.values().length,
            metrics.size()
        );
    }

}
