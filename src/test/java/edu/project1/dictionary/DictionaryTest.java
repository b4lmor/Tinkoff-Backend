package edu.project1.dictionary;

import edu.project1.entity.word.WordEntity;
import edu.project1.enums.Topic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class DictionaryTest {

    private static Stream<Arguments> provideTopics() {
        return Stream.of(
            Arguments.of(Topic.SPORT),
            Arguments.of(Topic.FOOD)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTopics")
    @DisplayName("Test Dictionary")
    void testDictionary(Topic topic) {

        Assertions.assertDoesNotThrow(() -> {
            ADictionary dictionary = DictionaryFactory.getDictionaryByTopic(topic);
            WordEntity wordEntity = dictionary.pickRandomWord();
        });

    }
}
