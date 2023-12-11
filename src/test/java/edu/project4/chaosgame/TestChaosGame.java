package edu.project4.chaosgame;

import edu.project4.entity.FractalImage;
import edu.project4.transformation.fractalflamevariations.Heart;
import edu.project4.transformation.fractalflamevariations.Popcorn;
import edu.project4.transformation.fractalflamevariations.Spiral;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestChaosGame {

    public static Stream<Arguments> ProvideParameters() {
        return Stream.of(
            Arguments.of(new ChaosGameOneThread()),
            Arguments.of(new ChaosGameMultiThreaded())
        );
    }

    @ParameterizedTest
    @MethodSource("ProvideParameters")
    void testChaosGame(ChaosGame chaosGame) {
        final int size = 400;
        FractalImage fractalImage = FractalImage.create(size, size);
        Rectangle2D.Double rect = new Rectangle2D.Double(-2, -2, 2, 2);

        fractalImage = chaosGame.iterate(
            List.of(
                new Heart()
            ),
            8,
            1_000,
            3,
            rect,
            fractalImage
        );

        FractalImage emptyFractalImage = FractalImage.create(400, 400);

        var flag = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var pixel = fractalImage.getPixel(i, j);
                var emptyPixel = emptyFractalImage.getPixel(i, j);

                if (!pixel.equals(emptyPixel)) {
                    flag = true;
                    break;
                }
            }
        }

        assertTrue(flag);
    }
}
