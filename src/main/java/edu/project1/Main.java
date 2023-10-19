package edu.project1;

import edu.project1.game.HangmanGame;
import edu.project1.renderer.HangmanRenderer;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        HangmanRenderer hangmanRenderer = new HangmanRenderer();

        hangmanRenderer.renderGreeting();
        game.runGame();
    }
}
