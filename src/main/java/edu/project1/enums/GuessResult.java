package edu.project1.enums;

import org.jetbrains.annotations.NotNull;

public enum GuessResult implements GameStage {
    WIN("You won! Congratulations!"),

    DEFEAT("Game over! You lost, the stick-guy is dead because of you :("),

    SUCCESSFUL_GUESS("right!"),

    FAILED_GUESS("Oops, this time you were wrong!"),

    START_STAGE("");

    private final String message;

    GuessResult(String message) {
        this.message = message;
    }

    @Override
    public @NotNull String getMessage() {
        return message;
    }
}
