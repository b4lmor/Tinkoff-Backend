package edu.hw6.task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StreamComposerTest {

    private static final String PATH = "src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt";
    private static final String MESSAGE
        = "Programming is learned by writing programs. ― Brian Kernighan";

    @Test
    void testStreamComposer() {

        File fileToCopy = new File(PATH);

        try {
            if(!fileToCopy.exists()) {
                fileToCopy.createNewFile();
            }
        } catch (IOException e) {
            fail();
        }

        Path path = Paths.get(PATH);

        StreamComposer.composeStreams(path);

        try (FileInputStream fileInputStream
                 = new FileInputStream(PATH);
             InputStreamReader inputStreamReader
                 = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader reader
                 = new BufferedReader(inputStreamReader)
        ) {
            String str = reader.readLine();

            assertEquals(MESSAGE, str);

        } catch (IOException e) {
            fail();
        }
    }
}
