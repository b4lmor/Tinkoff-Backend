package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("MagicNumber")
public class DateParser {
    private static final String DASH_SEPARATOR = "-";
    private static final String SLASH_SEPARATOR = "/";
    private static final String SPACE_SEPARATOR = " ";
    private static final String TWENTY = "20";

    public Optional<LocalDate> parse(String rawDate, Pattern.PatternName patternName) {
        return switch (patternName) {

            case DATE_WITH_DASH_1, DATE_WITH_DASH_2 -> parserDash(rawDate);

            case DATE_WITH_SLASH_1, DATE_WITH_SLASH_2 -> parserSlash(rawDate);

            case TOMORROW -> Optional.of(
                LocalDate.now()
                    .plusDays(1)
            );

            case TODAY -> Optional.of(
                LocalDate.now()
            );

            case YESTERDAY -> Optional.of(
                LocalDate.now()
                    .minusDays(1)
            );

            case N_DAY_AGO, N_DAYS_AGO -> parserAgo(rawDate);
        };
    }

    private Optional<LocalDate> parserDash(String rawDate) {
        String[] parts = rawDate.split(DASH_SEPARATOR);

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return Optional.of(
          LocalDate.of(
              year, month, day
          )
        );
    }

    private Optional<LocalDate> parserSlash(String rawDate) {
        String[] parts = rawDate.split(SLASH_SEPARATOR);

        int year = Integer.parseInt(
            parts[2].length() == 4
                ? parts[2]
                : TWENTY + parts[2]
        );

        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[0]);

        return Optional.of(
            LocalDate.of(
                year, month, day
            )
        );
    }

    private Optional<LocalDate> parserAgo(String rawDate) {
        String[] parts = rawDate.split(SPACE_SEPARATOR);
        return Optional.of(
            LocalDate.now()
                .minusDays(
                    Integer.parseInt(parts[0])
                )
        );
    }
}
