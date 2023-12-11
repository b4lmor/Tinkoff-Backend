package edu.project4.painter;

import edu.project4.entity.FractalImage;
import edu.project4.image.Painter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPainter {

    private final static Logger LOGGER = LogManager.getLogger();

    private static final String PNG_FILE_PATH = "src/main/resources/fractals/test.png";

    private static Painter painter;

    @BeforeAll
    static void init() {
        painter = new Painter();
    }

    @AfterAll
    static void deleteFileIfExists() {
        File file = new File(PNG_FILE_PATH);

        if (file.exists()) {
            if (file.delete()) {
                LOGGER.info("Файл успешно удален.");
                return;
            }
        }
        LOGGER.info("Не удалось удалить файл.");
    }

    @Test
    void testSave() throws IOException {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        painter.save(fractalImage, Path.of(PNG_FILE_PATH));
    }

    @Test
    void testGenerateImage() {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        var image = painter.generateImage(fractalImage);

        assertTrue(isBlackImage(image));
    }

    private boolean isBlackImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                if (pixelColor.getRed() != 0
                    || pixelColor.getGreen() != 0
                    || pixelColor.getBlue() != 0) {

                    return false;
                }
            }
        }

        return true;
    }
}
