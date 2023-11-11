package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileCloner {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String COPY_NAME_ADDITION = " - copy";
    private static final String DOT = ".";

    private FileCloner() {
    }

    public static int cloneFile(Path path) {

        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf(DOT));
        String extension = fileName.substring(fileName.lastIndexOf(DOT));

        int copyNumber = 1;
        Path copyPath = path.resolveSibling(baseName + COPY_NAME_ADDITION + extension);

        while (Files.exists(copyPath)) {
            copyPath = path.resolveSibling(
                baseName
                    + COPY_NAME_ADDITION
                    + " (" + ++copyNumber + ")"
                    + extension
            );
        }

        try {
            Files.copy(path, copyPath);
            return copyNumber;

        } catch (IOException e) {
            LOGGER.info("Can't copy the file:" + e.getMessage());
        }

        return -1;
    }
}
