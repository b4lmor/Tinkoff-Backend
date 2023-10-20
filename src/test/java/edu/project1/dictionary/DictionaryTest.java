package edu.project1.dictionary;

import edu.project1.entity.WordEntity;
import edu.project1.enums.Topic;
import edu.project1.scanner.MyScanner;
import edu.project1.utils.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DictionaryTest {
    private static final String SCENARIO_PATH = "src/main/resources/hangman/screenplay/scenario.txt";
    private final MyScanner myScanner = new MyScanner();

    private static Stream<Arguments> provideTopics() {
        return Stream.of(
            Arguments.of(Topic.SPORT),
            Arguments.of(Topic.FOOD)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTopics")
    @DisplayName("Test Dictionary")
    void testDictionaryByTopic(Topic topic) {

        Assertions.assertDoesNotThrow(() -> {
            AbstractDictionary dictionary = DictionaryFactory.getDictionaryByTopic(topic);
            WordEntity wordEntity = dictionary.pickRandomWord();
        });

    }

    private static Stream<Arguments> provideInputs() {
        return Stream.of(
            Arguments.of(List.of("1")),
            Arguments.of(Arrays.asList("0", "-11", "abc", "1")),
            Arguments.of(Arrays.asList("0", "-11", "abc", "2"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputs")
    @DisplayName("Test Dictionary")
    void testDictionaryByInput(List<String> scenarioLines) {

        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        Assertions.assertDoesNotThrow(() -> {
            AbstractDictionary dictionary = DictionaryFactory.getDictionaryByInput();
            WordEntity wordEntity = dictionary.pickRandomWord();
        });

        myScanner.setDefaultInput();
    }
}
