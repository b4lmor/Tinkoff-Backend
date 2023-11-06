package edu.hw5.task3.chain;

import edu.hw5.task3.DateParser;
import edu.hw5.task3.Pattern;
import edu.hw5.task3.Pattern.PatternName;
import java.time.LocalDate;
import java.util.Optional;

public class PatternChain {

    private final PatternName pattern;
    private PatternChain nextParser;

    public PatternChain(PatternName pattern) {
        this.pattern = pattern;
    }

    public void setNextParser(PatternChain parser) {
        this.nextParser = parser;
    }

    public Optional<LocalDate> parsePattern(String rawDate) {

        PatternName rawDatePattern = Pattern.getPattern(rawDate);

        if (this.pattern == rawDatePattern) {
            return (new DateParser()).parse(rawDate, rawDatePattern);

        } else if (this.nextParser != null) {
            return this.nextParser.parsePatternNext(rawDate, rawDatePattern);
        }

        return Optional.empty();
    }

    public static PatternChain initialize() {
        return initialize(0);
    }

    private static PatternChain initialize(int index) {
        PatternChain patternMember = new PatternChain(
                PatternName.values()[index]
        );

        patternMember.setNextParser(
            index == PatternName.values().length - 1
                ? null
                : initialize(index + 1)
        );

        return patternMember;
    }

    private Optional<LocalDate> parsePatternNext(String rawDate, PatternName pattern) {

        if (this.pattern == pattern) {
            return (new DateParser()).parse(rawDate, pattern);

        } else if (this.nextParser != null) {
            return this.nextParser.parsePatternNext(rawDate, pattern);
        }

        return Optional.empty();
    }

}
