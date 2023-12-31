package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class FrequencyDictionary {
    private FrequencyDictionary() {
    }

    public static <T> Map<T, Integer> countFrequency(@NotNull List<T> objects) {
        Map<T, Integer> frequencyDictionary = new HashMap<>();

        for (var obj : objects) {
            frequencyDictionary.merge(obj, 1, Integer::sum);
        }

        return frequencyDictionary;
    }
}
