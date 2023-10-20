package edu.project1.session;

import edu.project1.entity.WordEntity;
import edu.project1.enums.GuessResult;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.scanner.MyScanner;
import java.util.HashSet;
import java.util.Set;
import static edu.project1.enums.GuessResult.DEFEAT;
import static edu.project1.enums.GuessResult.FAILED_GUESS;
import static edu.project1.enums.GuessResult.START_STAGE;
import static edu.project1.enums.GuessResult.SUCCESSFUL_GUESS;
import static edu.project1.enums.GuessResult.WIN;
import static edu.project1.game.GameProperties.MAX_ATTEMPTS;
import static edu.project1.game.GameProperties.STOP_GAME_CHAR;

public class Session {
    private static final HangmanRenderer HANGMAN_RENDERER = new HangmanRenderer();
    private static final MyScanner SCANNER = new MyScanner();

    private static final String STOP_COMMAND_TIP = "We're going to start! If you want to stop the game, send '!stop'.";
    private static final String ANSWER_MESSAGE = "the word: ";

    private final WordEntity answer;
    private final Set<Character> userGuesses = new HashSet<>();
    private int attempts = 0;
    private GuessResult result = START_STAGE;

    public Session(WordEntity answer) {

        HANGMAN_RENDERER.renderMessage(STOP_COMMAND_TIP);
        HANGMAN_RENDERER.renderWordDifficulty(answer.difficulty());

        this.answer = answer;
    }

    public void makeGuess() {
        if (isGameFinished()) {
            return;
        }

        HANGMAN_RENDERER.renderStage(attempts);
        HANGMAN_RENDERER.renderHiddenAnswer(this, answer.word());
        HANGMAN_RENDERER.renderAlphabet(this);

        char guess = SCANNER.scanInputGuessPersistently(this);

        if (guess == STOP_GAME_CHAR) {
            result = giveUp();

        } else if (answer.word().contains(String.valueOf(guess))) {
            userGuesses.add(guess);

            if (isAnswerGuessed()) {
                result = WIN;
            } else {
                result = SUCCESSFUL_GUESS;
            }

        } else {
            userGuesses.add(guess);

            if (++attempts == MAX_ATTEMPTS) {
                result = DEFEAT;
            } else {
                result = FAILED_GUESS;
            }
        }

        HANGMAN_RENDERER.renderMessage(result.getMessage());
    }

    public boolean isAnswerGuessed() {
        return allCharsInSet(answer.word(), userGuesses);
    }

    public boolean isGameFinished() {
        switch (result) {
            case WIN, DEFEAT -> {
                HANGMAN_RENDERER.renderStage(getAttempts());
                HANGMAN_RENDERER.renderMessage(ANSWER_MESSAGE + answer.word());
                HANGMAN_RENDERER.renderMessage(result.getMessage());

                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public boolean isLetterUsed(char c) {
        return userGuesses.contains(c);
    }

    public int getAttempts() {
        return attempts;
    }

    private GuessResult giveUp() {
        attempts = MAX_ATTEMPTS;
        return DEFEAT;
    }

    private boolean allCharsInSet(String str, Set<Character> set) {
        return str.chars()
            .mapToObj(item -> (char) item)
            .allMatch(set::contains);
    }

}
