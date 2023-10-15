package edu.project1.entity.guess;

import org.jetbrains.annotations.NotNull;

public final class FailedGuess implements IGuessResult {
    private static final String FAILED_GUESS_MESSAGE = "Oops, this time you were wrong!";

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public @NotNull String getMessage() {
        return FAILED_GUESS_MESSAGE;
    }
}
