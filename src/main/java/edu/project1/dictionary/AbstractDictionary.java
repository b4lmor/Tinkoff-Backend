package edu.project1.dictionary;

import edu.project1.dictionary.json_reader.DictionaryReader;
import edu.project1.entity.WordEntity;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractDictionary {
    private final Random random = new Random();
    private final List<WordEntity> words;

    protected AbstractDictionary(String dictPath) throws IOException {
        words = DictionaryReader.readWordsFromDictionary(dictPath);
    }

    public @NotNull WordEntity pickRandomWord() {
        int dictionarySize = words.size();
        return words.get(getRandomNumber(dictionarySize));
    }

    private int getRandomNumber(int n) {
        return random.nextInt(n);
    }
}
