package edu.project3.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public record Configuration(
    String[] logPaths,
    LocalDate startDate,
    LocalDate endDate,
    Format format
) {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String PATH_PATTERN = "--path";
    private static final String FORMAT_PATTERN = "--format";
    private static final String FROM_DATE_PATTERN = "--from";
    private static final String TO_DATE_PATTERN = "--to";
    private static final String FLAG = "--";

    public static Configuration of(String[] args) {
        List<String> inputPaths = new ArrayList<>();
        LocalDate inputStartDate = null;
        LocalDate inputEndDate = null;
        Format inputFormat = Format.MARKDOWN;

        int i = 0;
        while (i < args.length) {
            var arg = args[i++];

            if (arg.equals(PATH_PATTERN)) {
                if (i < args.length) {
                    arg = args[i++];
                }
                while (i < args.length
                    && !(arg.startsWith(FLAG))) {
                    inputPaths.add(arg);
                    arg = args[i++];
                }

            }

            if (arg.equals(FROM_DATE_PATTERN)) {
                if (i < args.length) {
                    arg = args[i++];
                    inputStartDate = LocalDate.parse(arg, FORMATTER);
                }

            }

            if (arg.equals(TO_DATE_PATTERN)) {
                if (i < args.length) {
                    arg = args[i++];
                    inputEndDate = LocalDate.parse(arg, FORMATTER);
                }

            }

            if (arg.equals(FORMAT_PATTERN)) {
                if (i < args.length) {
                    arg = args[i++];
                    inputFormat = Format.getFormatByString(arg);
                }
            }
        }

        if (inputPaths.isEmpty()) {
            throw new IllegalArgumentException("You must pass at least one path.");
        }

        return new Configuration(
            inputPaths.toArray(new String[0]),
            inputStartDate,
            inputEndDate,
            inputFormat
        );
    }

    public enum Format {
        MARKDOWN,
        ADOC;

        public String getFileExtension() {
            return switch (this) {
                case MARKDOWN -> ".md";
                case ADOC -> ".adoc";
            };

        }

        public static Format getFormatByString(String rawFormat) {
            return switch (rawFormat) {
                case "markdown" -> MARKDOWN;
                case "adoc" -> ADOC;

                default -> MARKDOWN;
            };

        }
    }
}
