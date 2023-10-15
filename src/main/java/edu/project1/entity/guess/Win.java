package edu.project1.entity.guess;

import org.jetbrains.annotations.NotNull;

public final class Win implements IGuessResult {
    private static final String WIN_MESSAGE = "You won! Congratulations!";

    @Override
    public boolean isGameFinished() {
        return true;
    }

    @Override
    public @NotNull String getMessage() {
        return WIN_MESSAGE;
    }
}
