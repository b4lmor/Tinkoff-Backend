package edu.project1.game;

import edu.project1.dictionary.AbstractDictionary;
import edu.project1.dictionary.DictionaryFactory;
import edu.project1.entity.WordEntity;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.scanner.MyScanner;
import edu.project1.session.Session;
import java.io.IOException;
import static edu.project1.game.GameProperties.CAN_NOT_OPEN_DICTIONARY_MESSAGE;
import static edu.project1.game.GameProperties.CHANGE_TOPIC_MESSAGE;
import static edu.project1.game.GameProperties.PLAY_AGAIN_MESSAGE;

public class HangmanGame {
    private static AbstractDictionary dictionary = null;

    private HangmanGame() {
    }

    public static void runGame() {
        if (dictionary == null) {
            try {
                dictionary = DictionaryFactory.getDictionaryByInput();
            } catch (IOException ex) {
                HangmanRenderer.renderMessage(CAN_NOT_OPEN_DICTIONARY_MESSAGE);
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

    private static boolean playerWishToContinue() {
        return MyScanner.isAnswerYes();
    }

    private static boolean playerWantToChangeTopic() {
        return MyScanner.isAnswerYes();
    }

    private static void resetDictionary() {
        dictionary = null;
    }

    private static void proposePlayAgain() {
        HangmanRenderer.renderMessage(PLAY_AGAIN_MESSAGE);
        if (playerWishToContinue()) {

            HangmanRenderer.renderMessage(CHANGE_TOPIC_MESSAGE);
            if (playerWantToChangeTopic()) {
                resetDictionary();
                runGame();

            } else {
                runGame();
            }
        }
    }

}
