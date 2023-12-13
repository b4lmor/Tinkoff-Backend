package edu.hw10.task1;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.validation.Max;
import edu.hw10.task1.validation.Min;
import edu.hw10.task1.validation.MyNotNull;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {

    @Test
    void testGenerator() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        var obj1 = (MyRecord) randomObjectGenerator.nextObject(MyRecord.class, "of");

        assertTrue(
            10 < obj1.value() && obj1.value() < 50
        );

        assertNotNull(obj1.string);

        assertDoesNotThrow(() -> {
            var obj2 = (MyClass) randomObjectGenerator.nextObject(MyClass.class);
        });
    }

    public record MyRecord(
        int value,
        String string,
        double salary
    ) {
        public static MyRecord of(@Min(10) @Max(50) int value, @MyNotNull String string) {
            return new MyRecord(value, string, 0);
        }

    }

    public class MyClass {
        private final int index;
        private final String name;

        public MyClass(int index, String name) {
            this.index = index;
            this.name = name;
        }
    }
}
