package edu.project1.session;

import edu.project1.entity.WordEntity;
import edu.project1.enums.GuessResult;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.scanner.MyScanner;
import java.util.HashSet;
import static edu.project1.enums.GuessResult.DEFEAT;
import static edu.project1.enums.GuessResult.FAILED_GUESS;
import static edu.project1.enums.GuessResult.START_STAGE;
import static edu.project1.enums.GuessResult.SUCCESSFUL_GUESS;
import static edu.project1.enums.GuessResult.WIN;
import static edu.project1.game.GameProperties.ANSWER_MESSAGE;
import static edu.project1.game.GameProperties.MAX_ATTEMPTS;
import static edu.project1.game.GameProperties.STOP_COMMAND_TIP;
import static edu.project1.game.GameProperties.STOP_GAME_CHAR;

public class Session {
    private final WordEntity answer;
    private final HashSet<Character> userGuesses = new HashSet<>();
    private int attempts = 0;
    private GuessResult result = START_STAGE;

    public Session(WordEntity answer) {

        HangmanRenderer.renderMessage(STOP_COMMAND_TIP);
        HangmanRenderer.renderWordDifficulty(answer.difficulty());

        this.answer = answer;
    }

    public void makeGuess() {
        if (isGameFinished()) {
            return;
        }

        HangmanRenderer.renderStage(attempts);
        HangmanRenderer.renderHiddenAnswer(this, answer.word());
        HangmanRenderer.renderAlphabet(this);

        char guess = MyScanner.scanInputGuessPersistently(this);

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

        HangmanRenderer.renderMessage(result.getMessage());
    }

    public boolean isAnswerGuessed() {
        return allCharsInSet(answer.word(), userGuesses);
    }

    public boolean isGameFinished() {
        switch (result) {
            case WIN, DEFEAT -> {
                HangmanRenderer.renderStage(getAttempts());
                HangmanRenderer.renderMessage(ANSWER_MESSAGE + answer.word());
                HangmanRenderer.renderMessage(result.getMessage());

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

    private boolean allCharsInSet(String str, HashSet<Character> set) {
        return str.chars()
            .mapToObj(item -> (char) item)
            .allMatch(set::contains);
    }

}
