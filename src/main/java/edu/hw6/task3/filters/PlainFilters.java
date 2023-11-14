package edu.hw6.task3.filters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class PlainFilters {

    private PlainFilters() {
    }

    public static final Predicate<Path> READABLE = Files::isReadable;
    public static final Predicate<Path> REGULAR = Files::isRegularFile;
}
