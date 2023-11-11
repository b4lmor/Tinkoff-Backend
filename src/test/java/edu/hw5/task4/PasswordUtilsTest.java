package edu.hw5.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordUtilsTest {

    @Test
    public void testValidation() {
        assertTrue(
            PasswordUtils.isValid(
                "abc|"
            )
        );

        assertFalse(
            PasswordUtils.isValid(
                "abcdef"
            )
        );
    }
}
