package edu.project1.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {
    private final static Logger LOGGER = LogManager.getLogger();

    private FileUtil() {
    }

    public static void writeLinesToFile(String filePath, List<String> lines) {
        try {

            Path path = Paths.get(filePath);

            Files.deleteIfExists(path);
            Files.createFile(path);

            BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true)
            );

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
