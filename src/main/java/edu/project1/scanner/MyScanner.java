package edu.project1.scanner;

import edu.project1.enums.Topic;
import edu.project1.renderer.HangmanRenderer;
import edu.project1.session.Session;
import java.util.Scanner;
import static edu.project1.game.GameProperties.ASK_FOR_TOPIC;
import static edu.project1.game.GameProperties.BAD_LETTER_INPUT_MESSAGE;
import static edu.project1.game.GameProperties.BAD_TOPIC_INPUT_MESSAGE;
import static edu.project1.game.GameProperties.STOP_WORD;

public class MyScanner {
    private static final Scanner SCANNER = new Scanner(System.in);

    private MyScanner() {
    }

    public static Topic askForTopicPersistently() {
        while (true) {
            try {
                return askForTopic();
            } catch (Exception ex) {
                HangmanRenderer.renderMessage(BAD_TOPIC_INPUT_MESSAGE);
            }
        }
    }

    public static boolean isAnswerYes() {
        while (true) {
            String input = SCANNER.next();

            if (input.length() != 1) {
                continue;
            }

            char choice = input.charAt(0);

            if (choice != 'Y' && choice != 'n') {
                continue;
            }

            return choice == 'Y';
        }
    }

    public static char scanInputGuessPersistently(Session session) {
        while (true) {
            String input = SCANNER.next().toLowerCase();

            if (isStopWord(input)) {
                return '!';
            }

            if (input.length() != 1) {
                HangmanRenderer.renderMessage(BAD_LETTER_INPUT_MESSAGE);
                continue;
            }

            char guess = input.toLowerCase().charAt(0);

            if (!Character.isAlphabetic(guess)
                || session.isLetterUsed(guess)) {
                HangmanRenderer.renderMessage(BAD_LETTER_INPUT_MESSAGE);
                continue;
            }

            return guess;
        }
    }

    private static Topic askForTopic() {
        HangmanRenderer.renderMessage(ASK_FOR_TOPIC);
        HangmanRenderer.renderEnum(Topic.class);

        int number = SCANNER.nextInt();

        return Topic.values()[number - 1];
    }

    private static boolean isStopWord(String input) {
        return input.equals(STOP_WORD);
    }
}
