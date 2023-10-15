package edu.project1.entity.guess;

import org.jetbrains.annotations.NotNull;

public final class Defeat implements IGuessResult {
    private static final String DEFEAT_MESSAGE = "Game over! You lost, the stick-guy is dead because of you :(";

    @Override
    public boolean isGameFinished() {
        return true;
    }

    @Override
    public @NotNull String getMessage() {
        return DEFEAT_MESSAGE;
    }
}
