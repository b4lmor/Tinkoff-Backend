package edu.project1.enums;

import org.jetbrains.annotations.NotNull;

public enum GuessResult implements GameStage {
    WIN {
        private static final String WIN_MESSAGE = "You won! Congratulations!";

        @Override
        public @NotNull String getMessage() {
            return WIN_MESSAGE;
        }
    },

    DEFEAT {
        private static final String DEFEAT_MESSAGE = "Game over! You lost, the stick-guy is dead because of you :(";

        @Override
        public @NotNull String getMessage() {
            return DEFEAT_MESSAGE;
        }
    },

    SUCCESSFUL_GUESS {
        private static final String SUCCESSFUL_GUESS_MESSAGE = "right!";

        @Override
        public @NotNull String getMessage() {
            return SUCCESSFUL_GUESS_MESSAGE;
        }
    },

    FAILED_GUESS {
        private static final String FAILED_GUESS_MESSAGE = "Oops, this time you were wrong!";

        @Override
        public @NotNull String getMessage() {
            return FAILED_GUESS_MESSAGE;
        }
    },

    START_STAGE {

        @Override
        public @NotNull String getMessage() {
            return "";
        }
    }
}
