package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeManager {
    private static final String TIME_PATTERN =
        "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}";
    private static final String SEPARATOR = " - ";
    private static final String TIME_INTERVAL_PATTERN =
        TIME_PATTERN + SEPARATOR + TIME_PATTERN;

    private static final DateTimeFormatter DATE_TIME_PATTERN =
        DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private TimeManager() {
    }

    public static String getAvg(List<String> intervals) {
        if (isNotValid(intervals)) {
            throw new IllegalArgumentException();
        }

        List<Duration> durations = new ArrayList<>();

        for (var interval : intervals) {
            String[] splitTimes = interval.split(SEPARATOR);

            LocalDateTime start =
                LocalDateTime.parse(
                    splitTimes[0],
                    DATE_TIME_PATTERN
                );

            LocalDateTime end =
                LocalDateTime.parse(
                    splitTimes[1],
                    DATE_TIME_PATTERN
                );

            durations.add(
                Duration.between(start, end)
            );
        }

        var totalSeconds = durations.stream()
            .mapToLong(
                Duration::getSeconds
            )
            .sum();

        Duration averageDuration = Duration.ofSeconds(totalSeconds / intervals.size());

        return String.format(
            "%dh %dm",
            averageDuration.toHours(),
            averageDuration.toMinutesPart()
        );
    }

    private static boolean isNotValid(List<String> intervals) {
        return !intervals.stream()
            .allMatch(
                i -> i.matches(TIME_INTERVAL_PATTERN)
            );
    }
}
