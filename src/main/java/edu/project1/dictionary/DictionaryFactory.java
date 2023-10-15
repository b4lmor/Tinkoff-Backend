package edu.project1.dictionary;

import edu.project1.dictionary.dicts.FoodDictionary;
import edu.project1.dictionary.dicts.SportDictionary;
import edu.project1.enums.Topic;
import edu.project1.scanner.MyScanner;
import java.io.IOException;

public class DictionaryFactory {

    private DictionaryFactory() {
    }

    public static ADictionary getDictionaryByTopic(Topic topic) throws IOException {
        return switch (topic) {
            case SPORT -> new SportDictionary();
            case FOOD -> new FoodDictionary();
        };
    }

    public static ADictionary getDictionaryByInput() throws IOException {
        Topic topic = MyScanner.askForTopicPersistently();
        return DictionaryFactory.getDictionaryByTopic(topic);
    }

}
