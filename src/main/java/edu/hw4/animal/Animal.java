package edu.hw4.animal;

import edu.hw4.error.ValidationError;
import edu.hw4.error.impl.BigBonedFishError;
import edu.hw4.error.impl.TooSmallDogError;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    private static final int DOG_PAWS = 4;
    private static final int CAT_PAWS = 4;
    private static final int FISH_PAWS = 0;
    private static final int SPIDER_PAWS = 8;
    private static final int BIRD_PAWS = 2;

    private static final int FISH_WEIGHT_UPPER_LIMIT = 1500;
    private static final int DOG_HEIGHT_BOTTOM_LIMIT = 10;

    public int paws() {
        return switch (type) {
            case CAT -> CAT_PAWS;
            case DOG -> DOG_PAWS;
            case BIRD -> BIRD_PAWS;
            case FISH -> FISH_PAWS;
            case SPIDER -> SPIDER_PAWS;
        };
    }

    public static List<Animal> sortByHeightAsc(List<Animal> entities) {
        return entities.stream()
            .sorted(
                (a1, a2) -> Integer.signum(a1.height - a2.height)
            )
            .toList();
    }

    public static List<Animal> sortByWeightDesc(List<Animal> entities) {
        return entities.stream()
            .sorted(
                (a1, a2) -> Integer.signum(a2.weight - a1.weight)
            )
            .toList();
    }

    public static Map<Type, Long> countTypes(List<Animal> entities) {
        return entities.stream()
            .collect(
                Collectors.groupingBy(
                    animal -> animal.type,
                    Collectors.counting()
                )
            );
    }

    public static Animal findObjWithLongestName(List<Animal> entities) {
        return entities.stream()
            .max(
                (a1, a2) -> Integer.signum(a1.name.length() - a2.name.length())
            )
            .orElseThrow(RuntimeException::new);
    }

    public static Sex findGenderMajority(List<Animal> entities) {
        return entities.stream()
            .collect(
                Collectors.groupingBy(animal -> animal.sex, Collectors.counting())
            )
            .entrySet()
            .stream().max(
                Map.Entry.comparingByValue()
            )
            .orElseThrow(RuntimeException::new)
            .getKey();
    }

    public static Map<Type, Animal> findHeaviestObjectsByTypes(List<Animal> entities) {
        return entities.stream()
            .collect(
                Collectors.toMap(
                    animal -> animal.type,
                    Function.identity(),
                    (a1, a2) -> a1.weight > a2.weight ? a1 : a2
                )
            );
    }

    public static Animal findOldest(List<Animal> entities) {
        return entities.stream()
            .min(
                (a1, a2) -> Integer.signum(a2.age - a1.age)
            )
            .orElseThrow(RuntimeException::new);
    }

    public static Optional<Animal> findHeaviestWithHeightLimit(List<Animal> entities, int heightLimit) {
        return entities.stream()
            .filter(a -> a.height <= heightLimit)
            .max(
                (a1, a2) -> Integer.signum(a1.weight - a2.weight)
            );
    }

    public static Integer countPaws(List<Animal> entities) {
        return entities.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> findAllWithDifferentPawNumberAndAge(List<Animal> entities) {
        return entities.stream()
            .filter(
                a -> a.paws() != a.age
            )
            .toList();
    }

    public static List<Animal> findAllBitersWithUpperHeightLimit(List<Animal> entities, int heightUpperLimit) {
        return entities.stream()
            .filter(Animal::bites)
            .filter(a -> a.height >= heightUpperLimit)
            .toList();
    }

    public static List<Animal> findAllWhereWeightMoreThanHeight(List<Animal> entities) {
        return entities.stream()
            .filter(a -> a.weight > a.height)
            .toList();
    }

    public static List<Animal> findAllWithNotOneWordName(List<Animal> entities) {
        return entities.stream()
            .filter(a -> a.name.split("[ \\-]").length > 1)
            .toList();
    }

    public static Boolean isThereDogWithHeightMoreThanValue(List<Animal> entities, int value) {
        return entities.stream()
            .anyMatch(
                a -> a.type == Type.DOG && a.height > value
            );

    }

    public static Integer countWeightWithAgeLimits(
        List<Animal> entities,
        int bottomAgeLimit,
        int upperAgeLimit) {

        return entities.stream()
            .filter(
                a -> a.age >= bottomAgeLimit && a.age <= upperAgeLimit
            )
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> sortByTypeAndSexAndName(List<Animal> entities) {
        return entities.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                .thenComparing(
                    Comparator.comparing(Animal::sex)
                )
                .thenComparing(
                    Comparator.comparing(Animal::name)
                )
            )
            .toList();
    }

    public static Boolean areSpidersBiteMoreOftenThanDogs(List<Animal> entities) {
        return entities.stream()
            .filter(a -> a.bites)
            .mapToInt(a ->
                a.type == Type.DOG
                ? 1
                : (a.type == Type.SPIDER)
                    ? -1
                    : 0
            ).sum() < 0;
    }

    public static @SafeVarargs Animal findHeaviestFish(List<Animal>... entities) {
        return Arrays.stream(entities)
            .map(
                Animal::findHeaviestFishInList
            )
            .max(
                Comparator.comparing(Animal::weight)
            )
            .orElseThrow(RuntimeException::new);
    }

    public static Map<String, Set<ValidationError>> findAllInvalid(List<Animal> entities) {
        return entities.stream()
            .collect(
                Collectors.toMap(
                    a -> a.name,
                    Animal::getErrors
                )
            )
            .entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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

    private static Animal findHeaviestFishInList(List<Animal> entities) {
        return entities.stream()
            .filter(a -> a.type == Type.FISH)
            .max(
                Comparator.comparing(Animal::weight)
            )
            .orElseThrow(RuntimeException::new);
    }

    private Set<ValidationError> getErrors() {

        Set<ValidationError> errors = new HashSet<>();

        switch (this.type) {
            case FISH -> {
                if (this.weight > FISH_WEIGHT_UPPER_LIMIT) {
                    errors.add(BigBonedFishError.get());
                }
            }
            case DOG -> {
                if (this.height < DOG_HEIGHT_BOTTOM_LIMIT) {
                    errors.add(TooSmallDogError.get());
                }
            }
            default -> {
            }
        }

        return errors;
    }
}
