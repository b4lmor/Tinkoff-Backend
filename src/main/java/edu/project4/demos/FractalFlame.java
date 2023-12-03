package edu.project4.demos;

import edu.project4.chaosgame.ChaosGame;
import edu.project4.chaosgame.ChaosGameMultiThreaded;
import edu.project4.chaosgame.ChaosGameOneThread;
import edu.project4.entity.FractalImage;
import edu.project4.image.Painter;
import edu.project4.processor.ImageProcessor;
import edu.project4.processor.impl.GammaCorrection;
import edu.project4.transformation.fractalflamevariations.Heart;
import edu.project4.transformation.fractalflamevariations.Polar;
import edu.project4.transformation.fractalflamevariations.Popcorn;
import edu.project4.transformation.fractalflamevariations.Spherical;
import edu.project4.transformation.fractalflamevariations.Spiral;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings({"MagicNumber", "UncommentedMain"})
public class FractalFlame {

    private FractalFlame() {
    }

    public static void main(String[] args) {
        Painter painter = new Painter();
        Rectangle2D.Double rect = new Rectangle2D.Double(-4, -3, 8, 6);
        ImageProcessor processor = new GammaCorrection();


        FractalImage fractalImage = FractalImage.create(1920, 1080);
        ChaosGame renderer = new ChaosGameOneThread();
        fractalImage = renderer.iterate(
                List.of(
                        new Popcorn(0.0, 1.0),
                        new Heart(),
                        new Spiral()
                ),
                8,
                1_000,
                3,
                rect,
                fractalImage
        );


        try {
            painter.save(fractalImage, Path.of("image.png"));

            processor.process(fractalImage);
            painter.save(fractalImage, Path.of("corrected-image.png"));
        } catch (IOException e) {
        }


        FractalImage fractalImageMT = FractalImage.create(1920, 1080);
        ChaosGame rendererMT = new ChaosGameMultiThreaded();
        fractalImageMT = rendererMT.iterate(
                List.of(
                        new Popcorn(1.7, 0.3),
                        //new ComplexMultiplication(new Complex(1.2, -2.19)),
                        new Spherical(),
                        new Heart(),
                        new Polar()

                ),
                8,
                50_000,
                6,
                rect,
                fractalImageMT
        );

        try {
            processor.process(fractalImageMT);
            painter.save(fractalImageMT, Path.of("mt-corrected-image.png"));
        } catch (IOException e) {
        }
    }
}
