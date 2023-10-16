package edu.project1;

import edu.project1.game.HangmanGame;
import edu.project1.renderer.HangmanRenderer;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        HangmanRenderer.renderGreeting();
        HangmanGame.runGame();
    }
}
