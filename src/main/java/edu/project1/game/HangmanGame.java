package edu.project1.game;

import edu.project1.dictionary.AbstractDictionary;
import edu.project1.dictionary.DictionaryFactory;
import edu.project1.entity.WordEntity;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.scanner.MyScanner;
import edu.project1.session.Session;
import java.io.IOException;

public class HangmanGame {
    private static final MyScanner SCANNER = new MyScanner();
    private static final HangmanRenderer HANGMAN_RENDERER = new HangmanRenderer();

    public static final String CAN_NOT_OPEN_DICTIONARY_MESSAGE = "Looks like the dictionary is broken. Sorry :(";
    public static final String PLAY_AGAIN_MESSAGE = "Do you wanna play again? (Y / n)";
    public static final String CHANGE_TOPIC_MESSAGE = "You can change the topic if you want. (Y / n)";

    private AbstractDictionary dictionary = null;

    public void runGame() {
        if (dictionary == null) {
            try {
                dictionary = DictionaryFactory.getDictionaryByInput();
            } catch (IOException ex) {
                HANGMAN_RENDERER.renderMessage(CAN_NOT_OPEN_DICTIONARY_MESSAGE);
                return;
            }
        }

        WordEntity answer = dictionary.pickRandomWord();
        Session session = new Session(answer);

        while (!session.isGameFinished()) {
            session.makeGuess();
        }

        proposePlayAgain();
    }

    private boolean playerWishToContinue() {
        return SCANNER.isAnswerYes();
    }

    private boolean playerWantToChangeTopic() {
        return SCANNER.isAnswerYes();
    }

    private void resetDictionary() {
        dictionary = null;
    }

    private void proposePlayAgain() {
        HANGMAN_RENDERER.renderMessage(PLAY_AGAIN_MESSAGE);
        if (playerWishToContinue()) {

            HANGMAN_RENDERER.renderMessage(CHANGE_TOPIC_MESSAGE);
            if (playerWantToChangeTopic()) {
                resetDictionary();
                runGame();

            } else {
                runGame();
            }
        }
    }

}
