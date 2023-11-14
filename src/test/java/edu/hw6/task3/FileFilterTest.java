package edu.hw6.task3;

import edu.hw6.task3.filters.NameMatchesFilter;
import edu.hw6.task3.filters.SizeLowerThanFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.filters.PlainFilters.READABLE;
import static edu.hw6.task3.filters.PlainFilters.REGULAR;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FileFilterTest {

    private static final String PATH = "src/main/resources/hw6";
    private static final String TEST_FILE_PATH = "src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt";

    @Test
    void testFileFilter() {

        File fileToCopy = new File(TEST_FILE_PATH);

        try {
            if(!fileToCopy.exists()) {
                fileToCopy.createNewFile();
            }
        } catch (IOException e) {
            fail();
        }

        FileFilter filter = FileFilter.of(
            READABLE,
            REGULAR
        );

        filter.and(new NameMatchesFilter("Tinkoff.*"))
            .and(new SizeLowerThanFilter(10000L));

        Path dir = Paths.get(PATH);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            boolean flag = false;
            for (var entry : entries) {
                if (entry.equals(Paths.get(TEST_FILE_PATH))) {
                    flag = true;
                }
            }
            assertTrue(flag);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
