package edu.hw6.task3.filters;

import java.nio.file.Path;
import java.util.function.Predicate;

public class NameMatchesFilter implements Predicate<Path> {

    private final String pattern;

    public NameMatchesFilter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean test(Path path) {
        return path.getFileName().toString().matches(pattern);
    }
}
