package edu.hw5.task7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryUtilsTest {

    @Test
    public void testContainsAtLeast3With0AtThirdPosition() {
        assertTrue(
            BinaryUtils.containsAtLeast3With0AtThirdPosition(
                "11011111"
            )
        );

        assertFalse(
            BinaryUtils.containsAtLeast3With0AtThirdPosition(
                "11"
            )
        );

        assertFalse(
            BinaryUtils.containsAtLeast3With0AtThirdPosition(
                "111111"
            )
        );

        assertFalse(
            BinaryUtils.containsAtLeast3With0AtThirdPosition(
                "abcd"
            )
        );
    }

    @Test
    public void testIsStartEqualsToEnd() {
        assertTrue(
            BinaryUtils.isStartEqualsToEnd(
                "1110001"
            )
        );

        assertFalse(
            BinaryUtils.isStartEqualsToEnd(
                "000011111"
            )
        );
    }

    @Test
    public void testIsLengthFrom1To3() {
        assertTrue(
            BinaryUtils.isLengthFrom1To3(
                "1"
            )
        );

        assertTrue(
            BinaryUtils.isLengthFrom1To3(
                "101"
            )
        );

        assertFalse(
            BinaryUtils.isLengthFrom1To3(
                "10101001"
            )
        );
    }
}
