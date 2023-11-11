package edu.hw5.task3;

import edu.hw5.task3.exception.NoSuchPatternException;
import java.util.Arrays;
import java.util.Map;

public class Pattern {

    private Pattern() {
    }

    public static final Map<PatternName, String> PATTERNS = Map.ofEntries(
        Map.entry(
            PatternName.DATE_WITH_DASH_1,
            "\\d{4}-\\d{2}-\\d{1}"
        ),
        Map.entry(
            PatternName.DATE_WITH_DASH_2,
            "\\d{4}-\\d{2}-\\d{2}"
        ),
        Map.entry(
            PatternName.DATE_WITH_SLASH_1,
            "\\d{1,1}/\\d{1,2}/\\d{4}"
        ),
        Map.entry(
            PatternName.DATE_WITH_SLASH_2,
            "\\d{1,2}/\\d{1,2}/\\d{2}"
        ),
        Map.entry(
            PatternName.TOMORROW,
            "tomorrow"
        ),
        Map.entry(
            PatternName.TODAY,
            "today"
        ),
        Map.entry(
            PatternName.YESTERDAY,
            "yesterday"
        ),
        Map.entry(
            PatternName.N_DAY_AGO,
            "\\d+ day ago"
        ),
        Map.entry(
            PatternName.N_DAYS_AGO,
            "\\d+ days ago"
        )
    );

    public enum PatternName {
        DATE_WITH_DASH_1,
        DATE_WITH_DASH_2,
        DATE_WITH_SLASH_1,
        DATE_WITH_SLASH_2,
        TOMORROW,
        TODAY,
        YESTERDAY,
        N_DAY_AGO,
        N_DAYS_AGO,
    }

    public static Pattern.PatternName getPattern(String rawDate) {
        return Arrays.stream(
                Pattern.PatternName.values()
            )
            .filter(
                p -> rawDate.matches(
                    PATTERNS.get(p)
                )
            )
            .findFirst()
            .orElseThrow(NoSuchPatternException::new);
    }
}
