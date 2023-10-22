package edu.hw3.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BackwardIteratorTest {

    @Test
    @DisplayName("Test Backward iterator")
    public void testBackwardIterator() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(
            List.of(1, 2, 3)
        );

        assertEquals(
            backwardIterator.next(),
            3
        );
        assertEquals(
            backwardIterator.next(),
            2
        );
        assertEquals(
            backwardIterator.next(),
            1
        );

        assertFalse(
            backwardIterator.hasNext()
        );
    }
}
