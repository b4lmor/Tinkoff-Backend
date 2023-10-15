package edu.project1.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project1.game.GameProperties.MAX_ATTEMPTS;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("Test Session Guessing")
    void TestSessionGuessing() {
        String answer = "test";
        Session session = new Session(answer);

        int attempts0 = session.getAttempts();

        session.makeGuess('t');

        int attempts1 = session.getAttempts();

        assertTrue(session.isLetterUsed('t'));
        assertEquals(attempts0, attempts1);

        session.makeGuess('t');

        int attempts2 = session.getAttempts();

        assertEquals(attempts0, attempts2);

        session.makeGuess('o');

        int attempts3 = session.getAttempts();

        assertNotEquals(attempts0, attempts3);

        session.makeGuess('e');
        session.makeGuess('s');

        assertTrue(session.isAnswerGuessed());

    }

    @Test
    @DisplayName("Test Session 'Give up'")
    void TestSessionGiveUp() {
        String answer = "test";
        Session session = new Session(answer);

        session.giveUp();

        int attempts = session.getAttempts();

        assertEquals(attempts, MAX_ATTEMPTS);
    }
}
