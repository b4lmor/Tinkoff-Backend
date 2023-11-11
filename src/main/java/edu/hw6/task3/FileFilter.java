package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.function.Predicate;

public class FileFilter implements DirectoryStream.Filter<Path> {

    private static Predicate<Path> filter = path -> true;

    @Override
    public boolean accept(Path entry) {
        return filter.test(entry);
    }

    @SafeVarargs public static FileFilter of(Predicate<Path>... predicates) {
        FileFilter fileFilter = new FileFilter();
        for (var predicate : predicates) {
            filter = filter.and(predicate);
        }

        return fileFilter;
    }

    public FileFilter and(Predicate<Path> predicate) {
        filter = filter.and(predicate);
        return this;
    }

}
