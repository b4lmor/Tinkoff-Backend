package edu.hw6.task1;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DiskMapTest {

    private static final List<String> KEYS = List.of("key1", "key2", "key3", "key4");
    private static final List<String> VALUES = List.of("val1", "val2", "val3", "val4");
    private static final Map<String, String> PROTOTYPE = new HashMap<>();

    static {
        for (int i = 0; i < KEYS.size(); i++) {
            PROTOTYPE.put(KEYS.get(i), VALUES.get(i));
        }
    }

    @Test
    void testDiskMap() {

        File file = new File("src/main/resources/hw6/diskmap.txt");

        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            fail();
        }

        DiskMap diskMap = new DiskMap("src/main/resources/hw6/diskmap.txt");

        assertDoesNotThrow(() -> {
            for (int i = 0; i < KEYS.size(); i++) {
                diskMap.put(KEYS.get(i), VALUES.get(i));
            }
        });

        assertEquals(
            "val3",
            diskMap.get("key3")
        );

        assertEquals(
            new HashSet<>(KEYS),
            diskMap.keySet()
        );

        assertTrue(
            VALUES.containsAll(
                diskMap.values()
            )
        );

        assertEquals(
            diskMap.size(),
            KEYS.size()
        );

        assertEquals(
            PROTOTYPE.entrySet(),
            diskMap.entrySet()
        );


        Map<String, String> map = Map.ofEntries(
            Map.entry("newKey", "newVal")
        );

        assertDoesNotThrow(() -> {
            diskMap.putAll(map);
        });

        assertTrue(
            diskMap.containsValue("newVal")
        );

        assertTrue(
            diskMap.containsKey("newKey")
        );

        assertDoesNotThrow(() -> {
            diskMap.remove("newKey");
        });

        assertFalse(
            diskMap.containsKey("newKey")
        );

    }
}
