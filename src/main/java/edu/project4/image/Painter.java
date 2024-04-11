package edu.project4.image;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class Painter {

    public void save(FractalImage image, Path fileName) throws IOException {
        ImageIO.write(generateImage(image), "PNG", fileName.toFile());
    }

    public BufferedImage generateImage(FractalImage image) {
        BufferedImage bufferedImage =
                new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        return bufferedImage;
    }

}
