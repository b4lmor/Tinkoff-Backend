package edu.project1.renderer;

import edu.project1.session.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class HangmanRenderer {
    private final static int DIFFICULTY_STAR_LENGTH = 10;
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String CROSS_OUT_SYMBOL = "\u0336"; // "O" + "\u0336" = "O̶"
    private final static Logger LOGGER = LogManager.getLogger();

    protected HangmanRenderer() {
    }

    public static void renderMessage(@NotNull String message) {
        LOGGER.info(message);
    }

    public static void renderGreeting() {
        for (var line : VisualData.HANGMAN_GREETING) {
            renderMessage(line);
        }
    }

    public static void renderStage(int stage) {
        if (stage < 0 || stage >= VisualData.HANGMAN_STAGES.length) {
            throw new IllegalArgumentException("Bad stage");
        }

        for (var line : VisualData.HANGMAN_STAGES[stage]) {
            renderMessage(line);
        }
    }

    public static <T extends Enum<T>> void renderEnum(Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        int n = 1;
        for (T value : enumValues) {
            renderMessage(String.valueOf(n++) + ". " + value);
        }
    }

    public static void renderWordDifficulty(int difficulty) {
        StringBuilder rating = new StringBuilder();
        for (int i = 0; i < DIFFICULTY_STAR_LENGTH; i++) {
            rating.append(i < difficulty ? "★" : "☆");
        }
        renderMessage("Difficulty of the word: " + rating);
    }

    public static void renderAlphabet(Session session) {
        StringBuilder alphabet = new StringBuilder();
        for (int i = 0; i < LOWERCASE_LETTERS.length(); i++) {
            char c = LOWERCASE_LETTERS.charAt(i);
            alphabet.append(
                session.isLetterUsed(c)
                    ? c + CROSS_OUT_SYMBOL
                    : c
                )
                .append(" ");
        }
        renderMessage("Available letters: " + alphabet);
    }

    public static void renderHiddenAnswer(Session session, String answer) {
        StringBuilder hiddenAnswer = new StringBuilder();
        for (int i = 0; i < answer.length(); i++) {
            char c = answer.charAt(i);
            hiddenAnswer.append(
                    session.isLetterUsed(c)
                        ? c
                        : "☐"
                );
        }
        renderMessage("The word: " + hiddenAnswer);
    }
}
