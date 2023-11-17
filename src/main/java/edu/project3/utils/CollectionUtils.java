package edu.project3.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionUtils {

    private CollectionUtils() {
    }

    @SuppressWarnings("MagicNumber")
    public static <T> Map<String, List<String>> getTop3MaxValues(Map<T, Integer> inputMap) {
        return inputMap.entrySet().stream()
            .sorted(
                Map.Entry.<T, Integer>comparingByValue().reversed()
            )
            .limit(3)
            .collect(Collectors.toMap(
                    e -> e.getKey().toString(),
                    e -> List.of((e.getValue().toString()))
                )
            );
    }
}
