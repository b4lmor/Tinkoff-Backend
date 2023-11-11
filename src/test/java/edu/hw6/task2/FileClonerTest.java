package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FileClonerTest {

    private static final String PATH = "src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt";
    private static final String COPY_ADDICTION = " - copy";

    @Test
    void testFileCloner() {
        Path filePath = Paths.get(PATH);

        File fileToCopy = new File(PATH);

        try {
            if(!fileToCopy.exists()) {
                fileToCopy.createNewFile();
            }
        } catch (IOException e) {
            fail();
        }

        FileCloner.cloneFile(filePath);
        int c = FileCloner.cloneFile(filePath); // 2 or more

        String baseName = PATH.substring(0, PATH.lastIndexOf("."));
        String extension = PATH.substring(PATH.lastIndexOf("."));

        assertTrue(
            Files.exists(Paths.get(baseName + COPY_ADDICTION + " (" + c + ")" + extension))
        );

    }
}
