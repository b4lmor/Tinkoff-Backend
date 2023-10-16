package edu.project1.dictionary.dicts;

import edu.project1.dictionary.AbstractDictionary;
import java.io.IOException;

public class FoodDictionary extends AbstractDictionary {
    private static final String FOOD_DICTIONARY_FILE_PATH =
        "src/main/resources/hangman/dicts/food_dict.json";

    public FoodDictionary() throws IOException {
        super(FOOD_DICTIONARY_FILE_PATH);
    }
}
