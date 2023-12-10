package edu.hw9.task2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileProcessor {

    private static FileSystemProcessor fileSystemProcessor;

    @BeforeAll
    static void init() {
        fileSystemProcessor = new FileSystemProcessor();
    }


    @Test
    void testFindDirectoriesWithMoreThanXFiles() {
        var paths = fileSystemProcessor.findDirectoriesWithMoreThanXFiles(
            "src/main/resources/hw9",
            2
        );

        assertEquals(
            2,
            paths.size()
        );

        for (var path : paths) {
            assertTrue(
                path.endsWith("hw9\\subdir1")
                || path.endsWith("hw9")
                || path.endsWith("hw9/subdir1") // for linux
            );
        }
    }

    @Test
    void testFindFilesByPredicate() {

        FileSystemProcessor.FilePredicate predicate = file -> file.getAbsolutePath().endsWith("file2");

        var paths = fileSystemProcessor.findFilesByPredicate(
            "src/main/resources/hw9",
            predicate
        );

        assertEquals(
            2,
            paths.size()
        );

        for (var path : paths) {
            assertTrue(
                path.endsWith("hw9\\subdir1\\file2")
                    || path.endsWith("hw9\\file2")
                    || path.endsWith("hw9/subdir1/file2") // for linux
                    || path.endsWith("hw9/file2") // for linux
            );
        }
    }
}
