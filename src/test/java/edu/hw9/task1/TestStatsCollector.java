package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestStatsCollector {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(
                "metric",
                new double[] {0.1, 0.05, 1.4, 5.1, 0.3},
                Map.ofEntries(
                    Map.entry("metric_sum", 0.1 + 0.05 + 1.4 + 5.1 + 0.3),
                    Map.entry("metric_avg", 1.39),
                    Map.entry("metric_max", 5.1),
                    Map.entry("metric_min", 0.05)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void testStatsCollector(
        String name,
        double[] values,
        Map<String, Double> expectedResult
    ) {

        var statsCollector = new StatsCollector();
        statsCollector.push(name, values);

        Map<String, Double> result = null;

        try {
            result = statsCollector.stats();
        } catch (InterruptedException | ExecutionException e) {
            fail();
        }

        assertEquals(
            expectedResult.entrySet(),
            result.entrySet()
        );
    }
}
