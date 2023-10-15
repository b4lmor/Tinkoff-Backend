package edu.project1.entity.guess;

import org.jetbrains.annotations.NotNull;

sealed public interface IGuessResult permits Defeat, FailedGuess, SuccessfulGuess, Win {
    boolean isGameFinished();

    @NotNull String getMessage();

}
