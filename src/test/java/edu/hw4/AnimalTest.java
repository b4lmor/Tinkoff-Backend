package edu.hw4;

import edu.hw4.animal.Animal;
import edu.hw4.animal.Sex;
import edu.hw4.animal.Type;
import edu.hw4.error.ValidationError;
import edu.hw4.error.impl.BigBonedFishError;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {

    private final List<Animal> entities = List.of(
        new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true),
        new Animal("Bettie",           Type.DOG,    Sex.FEMALE, 19, 120, 20, true),

        new Animal("Eight-eyed",       Type.SPIDER, Sex.MALE,   2,  5,   2,  true),
        new Animal("Black widow",      Type.SPIDER, Sex.FEMALE, 6,  8,   3,  true),
        new Animal("Cobwebby",         Type.SPIDER, Sex.MALE,   1,  4,   1,  false),

        new Animal("Kessie",           Type.CAT,    Sex.FEMALE, 11, 100, 15, false),
        new Animal("Meower",           Type.CAT,    Sex.MALE,   16, 130, 14, true),

        new Animal("Fly enjoyer",      Type.BIRD,   Sex.MALE,   7,  40,  5,  false),
        new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true),

        new Animal("swimmer",          Type.FISH,   Sex.MALE,   0,  50,  10, false),
        new Animal("bubble",           Type.FISH,   Sex.MALE,   5,  12,  18, false)
    );

    private final List<Animal> fishes = List.of(
        new Animal("fisher",          Type.FISH,   Sex.FEMALE, 53,  320,   500, false),
        new Animal("shark",           Type.FISH,   Sex.MALE,   54,  1090,  1800, true)
    );

    @Test
    public void testSortByHeightAsc() {
        List<Animal> sortedEntities = Animal.sortByHeightAsc(entities);

        List<Animal> expectedSortedEntities = List.of(
            new Animal("Cobwebby",         Type.SPIDER, Sex.MALE,   1,  4,   1,  false),
            new Animal("Eight-eyed",       Type.SPIDER, Sex.MALE,   2,  5,   2,  true),
            new Animal("Black widow",      Type.SPIDER, Sex.FEMALE, 6,  8,   3,  true),
            new Animal("bubble",           Type.FISH,   Sex.MALE,   5,  12,  18, false),
            new Animal("Fly enjoyer",      Type.BIRD,   Sex.MALE,   7,  40,  5,  false),
            new Animal("swimmer",          Type.FISH,   Sex.MALE,   0,  50,  10, false),
            new Animal("Kessie",           Type.CAT,    Sex.FEMALE, 11, 100, 15, false),
            new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true),
            new Animal("Bettie",           Type.DOG,    Sex.FEMALE, 19, 120, 20, true),
            new Animal("Meower",           Type.CAT,    Sex.MALE,   16, 130, 14, true),
            new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true)
        );

        assertEquals(
            sortedEntities,
            expectedSortedEntities
        );
    }

    @Test
    public void testSortByWeightDesc() {
        List<Animal> sortedEntities = Animal.sortByWeightDesc(entities);

        List<Animal> expectedSortedEntities = List.of(
            new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true),
            new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true),
            new Animal("Bettie",           Type.DOG,    Sex.FEMALE, 19, 120, 20, true),
            new Animal("bubble",           Type.FISH,   Sex.MALE,   5,  12,  18, false),
            new Animal("Kessie",           Type.CAT,    Sex.FEMALE, 11, 100, 15, false),
            new Animal("Meower",           Type.CAT,    Sex.MALE,   16, 130, 14, true),
            new Animal("swimmer",          Type.FISH,   Sex.MALE,   0,  50,  10, false),
            new Animal("Fly enjoyer",      Type.BIRD,   Sex.MALE,   7,  40,  5,  false),
            new Animal("Black widow",      Type.SPIDER, Sex.FEMALE, 6,  8,   3,  true),
            new Animal("Eight-eyed",       Type.SPIDER, Sex.MALE,   2,  5,   2,  true),
            new Animal("Cobwebby",         Type.SPIDER, Sex.MALE,   1,  4,   1,  false)
            );

        assertEquals(
            sortedEntities,
            expectedSortedEntities
        );
    }

    @Test
    public void testCountByTypes() {
        Map<Type, Long> countedTypes = Animal.countTypes(entities);

        Map<Type, Long> expectedCountedTypes = Map.ofEntries(
            Map.entry(Type.DOG, 2L),
            Map.entry(Type.SPIDER, 3L),
            Map.entry(Type.CAT, 2L),
            Map.entry(Type.BIRD, 2L),
            Map.entry(Type.FISH, 2L)
        );

        assertEquals(
            countedTypes,
            expectedCountedTypes
        );
    }

    @Test
    public void testFindObjWithLongestName() {
        Animal animal = Animal.findObjWithLongestName(entities);

        Animal expectedAnimal
            = new Animal("Eager eagle John", Type.BIRD, Sex.MALE, 18, 110, 30, true);

        assertEquals(
            animal,
            expectedAnimal
        );
    }

    @Test
    public void testFindGenderMajority() {
        Sex sex = Animal.findGenderMajority(entities);

        Sex expectedSex = Sex.MALE;

        assertEquals(
            sex,
            expectedSex
        );
    }

    @Test
    public void testFindHeaviestObjectsByTypes() {
        Map<Type, Animal> heaviestObjectsByTypes = Animal.findHeaviestObjectsByTypes(entities);

        Map<Type, Animal> expectedHeaviestObjectsByTypes = Map.ofEntries(
            Map.entry(Type.DOG, new Animal("Bark barker", Type.DOG, Sex.MALE, 15, 160, 40, true)),
            Map.entry(Type.SPIDER, new Animal("Black widow", Type.SPIDER, Sex.FEMALE, 6,  8, 3, true)),
            Map.entry(Type.CAT, new Animal("Kessie", Type.CAT, Sex.FEMALE, 11, 100, 15, false)),
            Map.entry(Type.BIRD, new Animal("Eager eagle John", Type.BIRD, Sex.MALE, 18, 110, 30, true)),
            Map.entry(Type.FISH, new Animal("bubble", Type.FISH, Sex.MALE, 5, 12,  18, false))
        );

        assertEquals(
            heaviestObjectsByTypes,
            expectedHeaviestObjectsByTypes
        );
    }

    @Test
    public void testFindOldest() {

        Animal animal = Animal.findOldest(entities);

        Animal expectedAnimal = new Animal("Bettie", Type.DOG, Sex.FEMALE, 19, 120, 20, true);

        assertEquals(
            animal,
            expectedAnimal
        );
    }

    @Test
    public void testFindHeaviestWithHeightLimit() {

        Optional<Animal> animal = Animal.findHeaviestWithHeightLimit(entities, 80);

        Optional<Animal> expectedAnimal = Optional.of(
            new Animal("bubble", Type.FISH, Sex.MALE, 5, 12, 18, false)
        );

        assertEquals(
            animal,
            expectedAnimal
        );
    }

    @Test
    public void testCountPaws() {

        int paws = Animal.countPaws(entities);

        int expectedPaws = 44;

        assertEquals(
            paws,
            expectedPaws
        );
    }

    @Test
    public void testFindAllWithDifferentPawNumberAndAge() {

        List<Animal> animals = Animal.findAllWithDifferentPawNumberAndAge(entities);

        List<Animal> expectedAnimals = List.of(
            new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true),
            new Animal("Bettie",           Type.DOG,    Sex.FEMALE, 19, 120, 20, true),
            new Animal("Eight-eyed",       Type.SPIDER, Sex.MALE,   2,  5,   2,  true),
            new Animal("Black widow",      Type.SPIDER, Sex.FEMALE, 6,  8,   3,  true),
            new Animal("Cobwebby",         Type.SPIDER, Sex.MALE,   1,  4,   1,  false),
            new Animal("Kessie",           Type.CAT,    Sex.FEMALE, 11, 100, 15, false),
            new Animal("Meower",           Type.CAT,    Sex.MALE,   16, 130, 14, true),
            new Animal("Fly enjoyer",      Type.BIRD,   Sex.MALE,   7,  40,  5,  false),
            new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true),
            new Animal("bubble",           Type.FISH,   Sex.MALE,   5,  12,  18, false)
        );

        assertEquals(
            animals,
            expectedAnimals
        );
    }

    @Test
    public void testFindAllBitersWithUpperHeightLimit() {

        List<Animal> animals = Animal.findAllBitersWithUpperHeightLimit(entities, 100);

        List<Animal> expectedAnimals = List.of(
            new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true),
            new Animal("Bettie",           Type.DOG,    Sex.FEMALE, 19, 120, 20, true),
            new Animal("Meower",           Type.CAT,    Sex.MALE,   16, 130, 14, true),
            new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true)
        );

        assertEquals(
            animals,
            expectedAnimals
        );
    }

    @Test
    public void testFindAllWhereWeightMoreThanHeight() {

        List<Animal> animals = Animal.findAllWhereWeightMoreThanHeight(entities);

        List<Animal> expectedAnimals = List.of(
            new Animal("bubble", Type.FISH, Sex.MALE, 5, 12, 18, false)
        );

        assertEquals(
            animals,
            expectedAnimals
        );
    }

    @Test
    public void testFindAllWithNotOneWordName() {

        List<Animal> animals = Animal.findAllWithNotOneWordName(entities);

        List<Animal> expectedAnimals = List.of(
            new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true),
            new Animal("Eight-eyed",       Type.SPIDER, Sex.MALE,   2,  5,   2,  true),
            new Animal("Black widow",      Type.SPIDER, Sex.FEMALE, 6,  8,   3,  true),
            new Animal("Fly enjoyer",      Type.BIRD,   Sex.MALE,   7,  40,  5,  false),
            new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true)
        );

        assertEquals(
            animals,
            expectedAnimals
        );
    }

    @Test
    public void testIsThereDogWithHeightMoreThanValue() {

        boolean isPresent = Animal.isThereDogWithHeightMoreThanValue(entities, 100);

        assertTrue(isPresent);
    }

    @Test
    public void testCountWeightWithAgeLimits() {
        Integer summaryWeight = Animal.countWeightWithAgeLimits(entities, 10, 20);

        Integer expectedSummaryWeight = 119;

        assertEquals(
            summaryWeight,
            expectedSummaryWeight
        );
    }

    @Test
    public void testSortByTypeAndSexAndName() {
        List<Animal> animals = Animal.sortByTypeAndSexAndName(entities);

        List<Animal> expectedAnimals = List.of(
            new Animal("Meower",           Type.CAT,    Sex.MALE,   16, 130, 14, true),
            new Animal("Kessie",           Type.CAT,    Sex.FEMALE, 11, 100, 15, false),

            new Animal("Bark barker",      Type.DOG,    Sex.MALE,   15, 160, 40, true),
            new Animal("Bettie",           Type.DOG,    Sex.FEMALE, 19, 120, 20, true),

            new Animal("Eager eagle John", Type.BIRD,   Sex.MALE,   18, 110, 30, true),
            new Animal("Fly enjoyer",      Type.BIRD,   Sex.MALE,   7,  40,  5,  false),

            new Animal("bubble",           Type.FISH,   Sex.MALE,   5,  12,  18, false),
            new Animal("swimmer",          Type.FISH,   Sex.MALE,   0,  50,  10, false),

            new Animal("Cobwebby",         Type.SPIDER, Sex.MALE,   1,  4,   1,  false),
            new Animal("Eight-eyed",       Type.SPIDER, Sex.MALE,   2,  5,   2,  true),
            new Animal("Black widow",      Type.SPIDER, Sex.FEMALE, 6,  8,   3,  true)
        );

        assertEquals(
            animals,
            expectedAnimals
        );
    }

    @Test
    public void testAreSpidersBiteMoreOftenThanDogs() {

        boolean isRight = Animal.areSpidersBiteMoreOftenThanDogs(entities);

        assertFalse(isRight);
    }

    @Test
    public void testFindHeaviestFish() {

        Animal animal = Animal.findHeaviestFish(entities, fishes);

        Animal expectedAnimal = new Animal("shark", Type.FISH, Sex.MALE, 54, 1090, 1800, true);

        assertEquals(
            animal,
            expectedAnimal
        );
    }

    @Test
    public void testFindAllInvalid() {
        Map<String, Set<ValidationError>> animalErrors = Animal.findAllInvalid(fishes);

        Map<String, Set<ValidationError>> expectedAnimalErrors = Map.ofEntries(
            Map.entry("shark", Set.of(BigBonedFishError.get()))
        );

        assertEquals(
            animalErrors,
            expectedAnimalErrors
        );
    }

    @Test
    public void testImproveErrorView() {
        Map<String, Set<ValidationError>> animalErrors = Animal.findAllInvalid(fishes);

        Map<String, String> improvedErrors = Animal.improveErrorView(animalErrors);

        Map<String, String> expectedImprovedErrors = Map.ofEntries(
            Map.entry("shark", "Fish can't be that fat.")
        );

        assertEquals(
            improvedErrors,
            expectedImprovedErrors
        );
    }

}
