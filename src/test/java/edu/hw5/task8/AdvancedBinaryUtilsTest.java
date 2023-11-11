package edu.hw5.task8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedBinaryUtilsTest {

    @Test
    public void testDoesNotContainSerialOnes() {
        assertTrue(
            AdvancedBinaryUtils.doesNotContainSerialOnes(
                "101010101"
            )
        );

        assertFalse(
            AdvancedBinaryUtils.doesNotContainSerialOnes(
                "1101111011"
            )
        );
    }

    @Test
    public void testDoesNotContainTowOrThreeOnes() {
        assertTrue(
            AdvancedBinaryUtils.doesNotContainTowOrThreeOnes(
                "101010101"
            )
        );

        assertFalse(
            AdvancedBinaryUtils.doesNotContainTowOrThreeOnes(
                "1101111011"
            )
        );
    }

    @Test
    public void testHasOddLength() {
        assertTrue(
            AdvancedBinaryUtils.hasOddLength(
                "101010101"
            )
        );

        assertFalse(
            AdvancedBinaryUtils.doesNotContainTowOrThreeOnes(
                "1111"
            )
        );
    }

    @Test
    public void testEveryOddDigitIsOne() {
        assertTrue(
            AdvancedBinaryUtils.everyOddDigitIsOne(
                "101110101"
            )
        );

        assertFalse(
            AdvancedBinaryUtils.doesNotContainTowOrThreeOnes(
                "110100000000"
            )
        );
    }


}
