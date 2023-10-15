package edu.project1.session;

import edu.project1.entity.guess.Defeat;
import edu.project1.entity.guess.FailedGuess;
import edu.project1.entity.guess.IGuessResult;
import edu.project1.entity.guess.SuccessfulGuess;
import edu.project1.entity.guess.Win;
import java.util.HashSet;
import org.jetbrains.annotations.NotNull;
import static edu.project1.game.GameProperties.MAX_ATTEMPTS;

public class Session {
    private final String answer;
    private final HashSet<Character> userGuesses = new HashSet<>();
    private int attempts = 0;

    public Session(String answer) {
        this.answer = answer;
    }

    public @NotNull IGuessResult makeGuess(char guess) {
        if (answer.contains(String.valueOf(guess))) {
            userGuesses.add(guess);

            if (isAnswerGuessed()) {
                return new Win();
            } else {
                return new SuccessfulGuess();
            }

        } else {
            if (++attempts == MAX_ATTEMPTS) {
                return new Defeat();
            } else {
                userGuesses.add(guess);
                return new FailedGuess();
            }
        }
    }

    public @NotNull IGuessResult giveUp() {
        attempts = MAX_ATTEMPTS;
        return new Defeat();
    }

    public boolean isAnswerGuessed() {
        return allCharsInSet(answer, userGuesses);
    }

    public boolean isLetterUsed(char c) {
        return userGuesses.contains(c);
    }

    public int getAttempts() {
        return attempts;
    }

    private boolean allCharsInSet(String str, HashSet<Character> set) {
        return str.chars()
            .mapToObj(item -> (char) item)
            .allMatch(set::contains);
    }
}
