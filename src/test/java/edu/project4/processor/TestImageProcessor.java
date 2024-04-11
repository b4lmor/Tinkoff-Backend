package edu.project4.processor;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.processor.impl.GammaCorrection;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class TestImageProcessor {

    @Test
    void testImageProcessor() {
        FractalImage fractalImage = FractalImage.create(100, 100);
        Pixel pixel = fractalImage.getPixel(0, 0);
        pixel.setRed(Color.red.getRed());

        var pixelCopy = new Pixel(
            pixel.getRed(), pixel.getGreen(), pixel.getBlue(), pixel.getHitCount()
        );

        ImageProcessor imageProcessor = new GammaCorrection();
        imageProcessor.process(fractalImage);

        assertNotEquals(pixel, pixelCopy);
    }
}
