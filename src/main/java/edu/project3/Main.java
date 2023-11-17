package edu.project3;

import edu.project3.entity.Configuration;
import edu.project3.logparser.NginxLogParser;
import edu.project3.logparser.metrics.Metric;
import edu.project3.printer.LogPrinter;
import edu.project3.printer.LogPrinterFabric;
import java.time.LocalDate;
import java.util.List;

public final class Main {

    private static final String[] LOG_PATHS = {
        //"src/main/resources/logparser/tmp.txt",
        "src/main/resources/logparser/nginx_logs.txt",
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
    };
    private static final LocalDate START_DATE = LocalDate.of(2015, 6, 1);
    private static final LocalDate END_DATE = LocalDate.of(2015, 6, 30);
    private static final Configuration.Format FORMAT = Configuration.Format.ADOC;
    private static final String OUTPUT_FILE_PATH
        = "src/main/resources/logparser/parsed" + FORMAT.getFileExtension();

    private Main() {
    }

    public static void main(String[] args) {

        Configuration configuration = new Configuration(
            LOG_PATHS,
            START_DATE,
            END_DATE,
            FORMAT
        );

//        Configuration configuration = Configuration.of(args);

        NginxLogParser parser = new NginxLogParser(configuration);

        List<Metric> metrics = parser.parseLogData();

        LogPrinterFabric logPrinterFabric = new LogPrinterFabric();

        LogPrinter logPrinter = logPrinterFabric.create(
            configuration.format(),
            OUTPUT_FILE_PATH
        );

        logPrinter.open();

        metrics.forEach(logPrinter::printMetric);

        logPrinter.close();
    }
}
