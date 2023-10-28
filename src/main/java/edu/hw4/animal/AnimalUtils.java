package edu.hw4.animal;

import edu.hw4.error.ValidationError;
import edu.hw4.error.impl.BigBonedFishError;
import edu.hw4.error.impl.TooSmallDogError;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnimalUtils {
    private AnimalUtils() {
    }

    public static Map<String, String> improveErrorView(Map<String, Set<ValidationError>> errors) {
        return errors.entrySet().stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    errs -> errs.getValue().stream().map(
                        ValidationError::getMessage
                    ).collect(Collectors.joining(System.lineSeparator()))
                )
            );
    }

}
