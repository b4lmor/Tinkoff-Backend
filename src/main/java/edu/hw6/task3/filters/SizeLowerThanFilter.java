package edu.hw6.task3.filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class SizeLowerThanFilter implements Predicate<Path> {

    private final long size;

    public SizeLowerThanFilter(long size) {
        this.size = size;
    }

    @Override
    public boolean test(Path path) {
        try {
            return Files.size(path) <= size;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
