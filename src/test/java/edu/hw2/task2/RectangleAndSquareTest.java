package edu.hw2.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class RectangleAndSquareTest {

    static Arguments[] provideRectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("provideRectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20)
            .setHeight(10);

        assertThat(rect.calculateArea())
            .isEqualTo(200.0);
    }
}
