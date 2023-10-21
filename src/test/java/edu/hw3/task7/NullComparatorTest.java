package edu.hw3.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NullComparatorTest {

    @Test
    @DisplayName("Test Null Comparator with Tree map")
    public void TestNullComparator() {

        Comparator<String> nullComparator = new NullComparator<>(String::compareTo);

        TreeMap<String, String> tree = new TreeMap<>(nullComparator);

        tree.put("firstKey", "firstValue");
        tree.put(null, "test");
        tree.put("", "empty");

        assertTrue(tree.containsKey(null));
        assertTrue(tree.containsKey("firstKey"));
        assertTrue(tree.containsKey(""));
    }
}
