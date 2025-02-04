package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ScoreboardTest {

    @Test
    public void startMatch() {
        // Given
        IScoreboard scoreboard = new Scoreboard();

        // When
        scoreboard.startMatch(new Team("Mexico"), new Team("Canada"));

        // Then
        List<String> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 0 - Canada 0", summary.get(0));
    }

    @Test
    public void startMatchWithSameTeamsThrowsException() {
        // Given
        IScoreboard scoreboard = new Scoreboard();

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            scoreboard.startMatch(new Team("Mexico"), new Team("Mexico"));
        });

        // Then
        assertEquals("Home team and away team cannot be the same.", exception.getMessage());
    }

    @Test
    public void updateScore() {
        // Given
        IScoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(new Team("Mexico"), new Team("Canada"));

        // When
        scoreboard.updateScore(new Team("Mexico"), new Team("Canada"), 0, 5);

        // Then
        List<String> summary = scoreboard.getSummary();
        assertEquals("Mexico 0 - Canada 5", summary.get(0));
    }

    @Test
    public void updateScoreWithNegativeValuesThrowsException() {
        // Given
        IScoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(new Team("Mexico"), new Team("Canada"));

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            scoreboard.updateScore(new Team("Mexico"), new Team("Canada"), -1, 5);
        });

        // Then
        assertEquals("Scores cannot be negative.", exception.getMessage());
    }

    @Test
    public void updateScoreForNonExistingMatchThrowsException() {
        // Given
        IScoreboard scoreboard = new Scoreboard();

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            scoreboard.updateScore(new Team("Mexico"), new Team("Canada"), 0, 5);
        });

        // Then
        assertEquals("Match not found.", exception.getMessage());
    }

    @Test
    public void finishMatch() {
        // Given
        IScoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(new Team("Mexico"), new Team("Canada"));

        // When
        scoreboard.finishMatch(new Team("Mexico"), new Team("Canada"));

        // Then
        List<String> summary = scoreboard.getSummary();
        assertTrue(summary.isEmpty());
    }

    @Test
    public void finishNonExistingMatchThrowsException() {
        // Given
        IScoreboard scoreboard = new Scoreboard();

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            scoreboard.finishMatch(new Team("Mexico"), new Team("Canada"));
        });

        // Then
        assertEquals("Match not found.", exception.getMessage());
    }

    @Test
    public void getSummaryShouldBeSuccessfulAndOrderedByTotalScore() {
        // Given
        IScoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(new Team("Mexico"), new Team("Canada"));
        scoreboard.updateScore(new Team("Mexico"), new Team("Canada"), 0, 5);
        scoreboard.startMatch(new Team("Spain"), new Team("Brazil"));
        scoreboard.updateScore(new Team("Spain"), new Team("Brazil"), 10, 2);
        scoreboard.startMatch(new Team("Germany"), new Team("France"));
        scoreboard.updateScore(new Team("Germany"), new Team("France"), 2, 2);
        scoreboard.startMatch(new Team("Uruguay"), new Team("Italy"));
        scoreboard.updateScore(new Team("Uruguay"), new Team("Italy"), 6, 6);
        scoreboard.startMatch(new Team("Argentina"), new Team("Australia"));
        scoreboard.updateScore(new Team("Argentina"), new Team("Australia"), 3, 1);

        // When
        List<String> summary = scoreboard.getSummary();

        // Then
        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - Italy 6", summary.get(0));
        assertEquals("Spain 10 - Brazil 2", summary.get(1));
        assertEquals("Mexico 0 - Canada 5", summary.get(2));
        assertEquals("Argentina 3 - Australia 1", summary.get(3));
        assertEquals("Germany 2 - France 2", summary.get(4));
    }
}