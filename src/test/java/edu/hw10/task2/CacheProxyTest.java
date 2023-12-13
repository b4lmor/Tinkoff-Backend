package edu.hw10.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheProxyTest {

    public interface FibCalculator {
        @Cache(persist = true)
        long fib(int number);
    }

    class FibCalculatorImpl implements FibCalculator {
        @Override
        public long fib(int number) {
            return number == 0 || number == 1
                ? 1
                : fib(number - 1) + fib(number - 2);
        }
    }

    @Test
    void testFigCacheProxy() throws NoSuchFieldException, IllegalAccessException, IOException {
        FibCalculator c = new FibCalculatorImpl();
        FibCalculator proxy = (FibCalculator) CacheProxy.create(c, FibCalculator.class);

        proxy.fib(5);
        proxy.fib(5);
        proxy.fib(7);
        proxy.fib(11);
        proxy.fib(4);
        proxy.fib(4);

        var pathField = CacheProxy.class.getDeclaredField("DATA_FILE_PATH");
        pathField.setAccessible(true);
        String filePath = (String) pathField.get(null);

        Set<String> lines = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        assertEquals(
            Set.of(
                "fib_11 -> 144",
                "fib_7 -> 21",
                "fib_5 -> 8",
                "fib_4 -> 5"
            ),
            lines
        );
    }
}
