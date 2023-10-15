package edu.project1.game;

import edu.project1.dictionary.ADictionary;
import edu.project1.dictionary.DictionaryFactory;
import edu.project1.entity.guess.IGuessResult;
import edu.project1.entity.word.WordEntity;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.scanner.MyScanner;
import edu.project1.session.Session;
import java.io.IOException;
import static edu.project1.game.GameProperties.ANSWER_MESSAGE;
import static edu.project1.game.GameProperties.CANT_OPEN_DICTIONARY_MESSAGE;
import static edu.project1.game.GameProperties.CHANGE_TOPIC_MESSAGE;
import static edu.project1.game.GameProperties.PLAY_AGAIN_MESSAGE;
import static edu.project1.game.GameProperties.STOP_COMMAND_TIP;
import static edu.project1.game.GameProperties.STOP_GAME_CHAR;

public class Game {
    private static ADictionary dictionary = null;

    private Game() {
    }

    public static void runGame() {
        if (dictionary == null) {
            try {
                dictionary = DictionaryFactory.getDictionaryByInput();
            } catch (IOException ex) {
                HangmanRenderer.renderMessage(CANT_OPEN_DICTIONARY_MESSAGE);
                return;
            }
        }

        WordEntity answer = dictionary.pickRandomWord();
        Session session = new Session(answer.word());

        HangmanRenderer.renderMessage(STOP_COMMAND_TIP);
        HangmanRenderer.renderWordDifficulty(answer.difficulty());

        IGuessResult guessResult;

        while (true) {
            HangmanRenderer.renderStage(session.getAttempts());
            HangmanRenderer.renderHiddenAnswer(session, answer.word());
            HangmanRenderer.renderAlphabet(session);

            char guessLetter = MyScanner.scanInputGuessPersistently(session);

            if (guessLetter == STOP_GAME_CHAR) {
                guessResult = session.giveUp();
            } else {
                guessResult = session.makeGuess(guessLetter);
            }

            if (guessResult.isGameFinished()) {
                HangmanRenderer.renderStage(session.getAttempts());
                HangmanRenderer.renderMessage(ANSWER_MESSAGE + answer.word());
                HangmanRenderer.renderMessage(guessResult.getMessage());
                break;
            }

            HangmanRenderer.renderMessage(guessResult.getMessage());
        }

        HangmanRenderer.renderMessage(PLAY_AGAIN_MESSAGE);
        if (playerWishToContinue()) {

            HangmanRenderer.renderMessage(CHANGE_TOPIC_MESSAGE);
            if (playerWantToChangeTopic()) {
                dictionary = null;
                runGame();

            } else {
                runGame();
            }
        }
    }

    private static boolean playerWishToContinue() {
        return MyScanner.isAnswerYes();
    }

    private static boolean playerWantToChangeTopic() {
        return MyScanner.isAnswerYes();
    }


}
