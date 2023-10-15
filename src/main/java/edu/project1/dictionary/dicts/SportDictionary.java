package edu.project1.dictionary.dicts;

import edu.project1.dictionary.ADictionary;
import java.io.IOException;

public class SportDictionary extends ADictionary {
    private static final String SPORT_DICTIONARY_FILE_PATH =
        "src/main/resources/hangman/dicts/sport_dict.json";

    public SportDictionary() throws IOException {
        super(SPORT_DICTIONARY_FILE_PATH);
    }
}
