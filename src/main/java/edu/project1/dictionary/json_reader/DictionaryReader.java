package edu.project1.dictionary.json_reader;

import edu.project1.entity.word.WordEntity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class DictionaryReader {
    private static final String WORD_LIST_KEY_NAME = "words";
    private static final String WORD_KEY_NAME = "word";
    private static final String DIFFICULTY_KEY_NAME = "difficulty";

    private DictionaryReader() {
    }

    public static ArrayList<WordEntity> readWordsFromDictionary(String dictionaryPath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(dictionaryPath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        JSONArray wordsArray = jsonObject.getJSONArray(WORD_LIST_KEY_NAME);

        ArrayList<WordEntity> words = new ArrayList<>();
        for (int i = 0; i < wordsArray.length(); i++) {

            JSONObject wordObject = wordsArray.getJSONObject(i);

            String word = wordObject.getString(WORD_KEY_NAME).toLowerCase();
            int difficulty = wordObject.getInt(DIFFICULTY_KEY_NAME);

            words.add(new WordEntity(word, difficulty));
        }

        return words;
    }
}
