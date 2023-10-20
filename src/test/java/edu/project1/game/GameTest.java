package edu.project1.game;

import edu.project1.scanner.MyScanner;
import edu.project1.utils.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GameTest {
    private static final String SCENARIO_PATH = "src/main/resources/hangman/screenplay/scenario.txt";
    private static final MyScanner myScanner = new MyScanner();

    @Test
    @DisplayName("Test Game Loosing Scenario")
    void testGameLoosingScenario() {
        List<String> scenarioLines = Arrays.asList(
            "Sport", "sport", "1",
            "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "n"
        );

        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        HangmanGame game = new HangmanGame();

        Assertions.assertDoesNotThrow(game::runGame);

        myScanner.setDefaultInput();
    }

    @Test
    @DisplayName("Test Game Loosing Scenario With Play Again")
    void testGameLoosingScenarioWithPlayAgain() {
        List<String> scenarioLines = Arrays.asList(
            "Sport", "sport", "1",
            "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "Y",
            "n",
            "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "n"
        );

        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        HangmanGame game = new HangmanGame();

        Assertions.assertDoesNotThrow(game::runGame);

        myScanner.setDefaultInput();
    }

    @Test
    @DisplayName("Test Game Loosing Scenario With 'Play Again' With 'Switch Dictionary'")
    void testGameLoosingScenarioWithPlayAgainWithSwitchDictionary() {
        List<String> scenarioLines = Arrays.asList(
            "Sport", "sport", "1",
            "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "Y",
            "Y",
            "2",
            "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "n"
        );

        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        myScanner.changeInput(new File(SCENARIO_PATH));

        HangmanGame game = new HangmanGame();

        Assertions.assertDoesNotThrow(game::runGame);

        myScanner.setDefaultInput();
    }
}
