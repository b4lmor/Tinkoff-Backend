package edu.project1.entity.guess;

import org.jetbrains.annotations.NotNull;

public final class SuccessfulGuess implements IGuessResult {
    private static final String SUCCESSFUL_GUESS_MESSAGE = "right!";

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public @NotNull String getMessage() {
        return SUCCESSFUL_GUESS_MESSAGE;
    }
}
