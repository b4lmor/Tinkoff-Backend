package edu.hw5.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubstringUtilsTest {

    @Test
    public void testSubstring() {
        assertTrue(
            SubstringUtils.isSubstring(
                "A123BE777",
                "E77"
            )
        );

        assertFalse(
            SubstringUtils.isSubstring(
                "123АВЕ777",
                "ABC"
            )
        );
    }
}
