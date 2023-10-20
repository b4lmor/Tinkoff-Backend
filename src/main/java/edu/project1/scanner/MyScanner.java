package edu.project1.scanner;

import edu.project1.enums.Topic;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.session.Session;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project1.game.GameProperties.STOP_GAME_CHAR;
import static edu.project1.game.GameProperties.STOP_WORD;

public class MyScanner {
    private static final HangmanRenderer HANGMAN_RENDERER = new HangmanRenderer();
    private static final Logger LOGGER = LogManager.getLogger();

    private static final char YES_CHAR = 'Y';
    private static final char NO_CHAR = 'n';
    private static final String CAN_NOT_OPEN_SCENARIO_MESSAGE = "Can't open the scenario.";
    private static final String BAD_LETTER_INPUT_MESSAGE = "Please, send a letter that you haven't used yet.";
    private static final String BAD_TOPIC_INPUT_MESSAGE = "Send a topic number.";
    private static final String ASK_FOR_TOPIC = "Please, choose a topic for a game! (send a number)";

    private static Scanner scanner = new Scanner(System.in);

    public void changeInput(File input) {
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            LOGGER.info(CAN_NOT_OPEN_SCENARIO_MESSAGE);
        }
    }

    public void setDefaultInput() {
        scanner.close();
        scanner = new Scanner(System.in);
    }

    public Topic askForTopicPersistently() {
        while (true) {
            try {
                return askForTopic();
            } catch (Exception ex) {
                HANGMAN_RENDERER.renderMessage(BAD_TOPIC_INPUT_MESSAGE);
            }
        }
    }

    public boolean isAnswerYes() {
        while (true) {
            String input = scanner.next();

            if (input.length() != 1) {
                continue;
            }

            char choice = input.charAt(0);

            if (choice != YES_CHAR && choice != NO_CHAR) {
                continue;
            }

            return choice == YES_CHAR;
        }
    }

    public char scanInputGuessPersistently(Session session) {
        while (true) {
            String input = scanner.next().toLowerCase();

            if (isStopWord(input)) {
                return STOP_GAME_CHAR;
            }

            if (input.length() != 1) {
                HANGMAN_RENDERER.renderMessage(BAD_LETTER_INPUT_MESSAGE);
                continue;
            }

            char guess = input.toLowerCase().charAt(0);

            if (!Character.isAlphabetic(guess)
                || session.isLetterUsed(guess)) {
                HANGMAN_RENDERER.renderMessage(BAD_LETTER_INPUT_MESSAGE);
                continue;
            }

            return guess;
        }
    }

    private Topic askForTopic() {
        HANGMAN_RENDERER.renderMessage(ASK_FOR_TOPIC);
        HANGMAN_RENDERER.renderEnum(Topic.class);

        String input = scanner.next();
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException();
        }

        int number = Integer.parseInt(input);

        return Topic.values()[number - 1];
    }

    private boolean isStopWord(String input) {
        return input.equals(STOP_WORD);
    }
}
