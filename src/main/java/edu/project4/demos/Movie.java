package edu.project4.demos;

import edu.project4.chaosgame.ChaosGame;
import edu.project4.chaosgame.ChaosGameMultiThreaded;
import edu.project4.curves.LissajousCurve;
import edu.project4.entity.FractalImage;
import edu.project4.image.Painter;
import edu.project4.processor.ImageProcessor;
import edu.project4.processor.impl.GammaCorrection;
import edu.project4.transformation.Rotation;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.fractalflamevariations.ComplexMultiplication;
import edu.project4.transformation.fractalflamevariations.Heart;
import edu.project4.transformation.fractalflamevariations.Polar;
import edu.project4.transformation.fractalflamevariations.Popcorn;
import edu.project4.transformation.fractalflamevariations.Spherical;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jcodec.api.awt.AWTSequenceEncoder;
import static edu.project4.chaosgame.ChaosGameUtils.peekRandom;

@SuppressWarnings({"MagicNumber", "UncommentedMain"})
public class Movie {

    private static final Logger LOGGER = LogManager.getLogger();

    private Movie() {
    }

    public static void main(String[] args) throws IOException {

        int seconds = 5;

        List<Transformation> variations = new ArrayList<>();
        variations.add(new Polar());

        var p = new Popcorn(1.7, 0.3);
        var s = new Spherical();
        var h = new Heart();
        var c = new ComplexMultiplication(1.7, 0.3);

        variations.add(p);
        variations.add(s);
        variations.add(h);
        variations.add(c);

        List<Transformation> functions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            functions.add(peekRandom(variations));
        }

        AWTSequenceEncoder enc = AWTSequenceEncoder.create25Fps(new File("out.mp4"));
        BufferedImage bi;
        Vector2D x;
        Vector2D y;

        DoubleFunction<Vector2D> curve = t -> new LissajousCurve(1.0, 0.5, 0.0)
                .apply(t).scalarMultiply(2.0);
        DoubleFunction<Vector2D> curve1 = t -> new LissajousCurve(1.3, 0.2, 0.1)
            .apply(t).scalarMultiply(1.8);

        FractalImage fractalImage = FractalImage.create(800, 800);
        Rectangle2D.Double rect = new Rectangle2D.Double(-2, -1.5, 4, 3);
        ChaosGame renderer = new ChaosGameMultiThreaded();
        Painter painter = new Painter();
        ImageProcessor processor = new GammaCorrection();

        int n = 25 * seconds;
        for (int i = 0; i < n; i++) {
            var r = new Rotation(((double) i / n) * 2 * Math.PI);

            x = curve.apply((double) i / n);
            r.apply(x);
            p.setC(x.getX());
            p.setF(x.getY());

            y = curve1.apply((double) i / n);
            r.apply(y);
            c.setReal(y.getX());
            c.setImaginary(y.getY());

            fractalImage = renderer.iterate(
                    functions,
                    8, 30_000, 6,
                    rect, fractalImage
            );

            LOGGER.info("Generated: {} \\ {}", i, n);

            processor.process(fractalImage);
            bi = painter.generateImage(fractalImage);
            enc.encodeImage(bi);

            fractalImage = FractalImage.create(800, 800);

        }
        enc.finish();
    }
}
