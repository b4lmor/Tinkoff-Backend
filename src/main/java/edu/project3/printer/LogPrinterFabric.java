package edu.project3.printer;

import edu.project3.entity.Configuration;
import edu.project3.printer.impl.AdocLogPrinter;
import edu.project3.printer.impl.MarkdownLogPrinter;

public class LogPrinterFabric {
    public LogPrinter create(Configuration.Format format, String filePath) {
        return switch (format) {
            case MARKDOWN -> new MarkdownLogPrinter(filePath);
            case ADOC -> new AdocLogPrinter(filePath);
        };
    }
}
