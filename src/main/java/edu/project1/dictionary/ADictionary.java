package edu.project1.dictionary;

import edu.project1.dictionary.json_reader.DictionaryReader;
import edu.project1.entity.word.WordEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public abstract class ADictionary {
    private final ArrayList<WordEntity> words;

    protected ADictionary(String dictPath) throws IOException {
        words = DictionaryReader.readWordsFromDictionary(dictPath);
    }

    public @NotNull WordEntity pickRandomWord() {
        int dictionarySize = words.size();
        return words.get(getRandomNumber(dictionarySize));
    }

    private static int getRandomNumber(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }
}
