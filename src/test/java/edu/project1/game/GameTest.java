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

    @Test
    @DisplayName("Test Game Loosing Scenario")
    void testGameLoosingScenario() {
        List<String> scenarioLines = Arrays.asList(
            "Sport", "sport", "1",
            "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "n"
        );

        FileUtil.writeLinesToFile(SCENARIO_PATH, scenarioLines);

        MyScanner.changeInput(new File(SCENARIO_PATH));

        Assertions.assertDoesNotThrow(HangmanGame::runGame);

        MyScanner.setDefaultInput();
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

        MyScanner.changeInput(new File(SCENARIO_PATH));

        Assertions.assertDoesNotThrow(HangmanGame::runGame);

        MyScanner.setDefaultInput();
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

        MyScanner.changeInput(new File(SCENARIO_PATH));

        Assertions.assertDoesNotThrow(HangmanGame::runGame);

        MyScanner.setDefaultInput();
    }
}
