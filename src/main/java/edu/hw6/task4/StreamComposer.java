package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamComposer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String MESSAGE
        = "Programming is learned by writing programs. ― Brian Kernighan";

    private StreamComposer() {
    }

    public static void composeStreams(Path path) {
        try (OutputStream fileOutputStream
                 = Files.newOutputStream(path);
             CheckedOutputStream checkedOutputStream
                 = new CheckedOutputStream(fileOutputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream
                 = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter
                 = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter
                 = new PrintWriter(outputStreamWriter)) {

            printWriter.println(MESSAGE);

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
