package edu.hw5.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarPlateUtilsTest {

    @Test
    public void testValidation() {
        assertTrue(
            CarPlateUtils.isValid(
                "A123BE777"
            )
        );

        assertFalse(
            CarPlateUtils.isValid(
                "123АВЕ777"
            )
        );
    }
}
