package edu.project1.session;

import edu.project1.entity.WordEntity;
import edu.project1.scanner.MyScanner;
import edu.project1.utils.FileUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static edu.project1.game.GameProperties.STOP_WORD;

public class SessionTest {
    private static final String SCENARIO_PATH = "src/main/resources/hangman/screenplay/scenario.txt";
    private final MyScanner myScanner = new MyScanner();

    @Test
    @DisplayName("Test Session Successful guessing")
    void testSessionSuccessfulGuessing() {
        WordEntity answer = new WordEntity("test", 10);

        List<String> scenarioLines = Arrays.asList("a", "b", "t", "e", "s", "n");
        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        Session session = new Session(answer);

        for (String scenarioLine : scenarioLines) {

            if (session.isGameFinished()) {
                break;
            }

            session.makeGuess();

            if (scenarioLine.length() == 1 && Character.isAlphabetic(scenarioLine.charAt(0))) {
                assertTrue(session.isLetterUsed(
                    scenarioLine.charAt(0)
                ));

            } else {
                assertFalse(session.isLetterUsed(
                    scenarioLine.charAt(0)
                ));
            }
        }

        assertTrue(session.isAnswerGuessed());

        myScanner.setDefaultInput();
    }

    @Test
    @DisplayName("Test Session Failed guessing")
    void testSessionFailedGuessing() {
        WordEntity answer = new WordEntity("test", 10);

        List<String> scenarioLines = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        Session session = new Session(answer);

        for (String scenarioLine : scenarioLines) {

            if (session.isGameFinished()) {
                break;
            }

            session.makeGuess();

            if (scenarioLine.length() == 1 && Character.isAlphabetic(scenarioLine.charAt(0))) {
                assertTrue(session.isLetterUsed(
                    scenarioLine.charAt(0)
                ));

            } else {
                assertFalse(session.isLetterUsed(
                    scenarioLine.charAt(0)
                ));
            }
        }

        assertFalse(session.isAnswerGuessed());

        myScanner.setDefaultInput();
    }

    @Test
    @DisplayName("Test Session '!stop' command")
    void testSessionStopCommand() {
        WordEntity answer = new WordEntity("test", 10);

        List<String> scenarioLines = Arrays.asList("a", "b", "c", "d", STOP_WORD, "f", "g", "h", "i", "j");
        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        Session session = new Session(answer);

        for (String scenarioLine : scenarioLines) {

            session.makeGuess();

            if (scenarioLine.equals(STOP_WORD)) {
                assertTrue(session.isGameFinished());
            }

        }

        assertTrue(session.isGameFinished());

        myScanner.setDefaultInput();
    }
}
