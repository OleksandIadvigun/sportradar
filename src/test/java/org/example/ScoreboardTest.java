package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    public void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    public void startMatch() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team B";
        Instant startTime = Instant.now();

        // When
        scoreboard.startMatch(homeTeam, awayTeam, startTime);

        // Then
        List<String> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Team A 0 - Team B 0", summary.get(0));
    }

    @Test
    public void startMatchWithSameTeamsThrowsException() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team A";
        Instant startTime = Instant.now();

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch(homeTeam, awayTeam, startTime);
        });

        // Then
        assertEquals("Home team and away team cannot be the same.", exception.getMessage());
    }

    @Test
    public void startMatchWithEmptyTeamNameThrowsException() {
        // Given
        String homeTeam = "";
        String awayTeam = "Team B";
        Instant startTime = Instant.now();

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch(homeTeam, awayTeam, startTime);
        });

        // Then
        assertEquals("Home team and away team cannot be the same.", exception.getMessage());
    }

    @Test
    public void updateScore() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team B";
        Instant startTime = Instant.now();
        scoreboard.startMatch(homeTeam, awayTeam, startTime);

        // When
        scoreboard.updateScore(homeTeam, awayTeam, 3, 2);

        // Then
        List<String> summary = scoreboard.getSummary();
        assertEquals("Team A 3 - Team B 2", summary.get(0));
    }

    @Test
    public void updateScoreWithNegativeValuesThrowsException() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team B";
        Instant startTime = Instant.now();
        scoreboard.startMatch(homeTeam, awayTeam, startTime);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore(homeTeam, awayTeam, -1, 2);
        });

        // Then
        assertEquals("Scores cannot be negative.", exception.getMessage());
    }

    @Test
    public void updateScoreForNonExistingMatchThrowsException() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team B";

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            scoreboard.updateScore(homeTeam, awayTeam, 3, 2);
        });

        // Then
        assertEquals("Match not found.", exception.getMessage());
    }

    @Test
    public void finishMatch() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team B";
        Instant startTime = Instant.now();
        scoreboard.startMatch(homeTeam, awayTeam, startTime);

        // When
        scoreboard.finishMatch(homeTeam, awayTeam);

        // Then
        List<String> summary = scoreboard.getSummary();
        assertTrue(summary.isEmpty());
    }

    @Test
    public void finishNonExistingMatchThrowsException() {
        // Given
        String homeTeam = "Team A";
        String awayTeam = "Team B";

        // When
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            scoreboard.finishMatch(homeTeam, awayTeam);
        });

        // Then
        assertEquals("Match not found.", exception.getMessage());
    }

    @Test
    public void getSummaryShouldBeOrderedByTotalScoreAndStartTime() {
        // Given
        Instant now = Instant.now();
        scoreboard.startMatch("Team A", "Team B", now);
        scoreboard.updateScore("Team A", "Team B", 1, 2); // Total score: 3
        scoreboard.startMatch("Team C", "Team D", now.plusSeconds(10));
        scoreboard.updateScore("Team C", "Team D", 3, 3); // Total score: 6
        scoreboard.startMatch("Team E", "Team F", now.plusSeconds(20));
        scoreboard.updateScore("Team E", "Team F", 0, 1); // Total score: 1
        scoreboard.startMatch("Team G", "Team H", now.plusSeconds(30));
        scoreboard.updateScore("Team G", "Team H", 1, 2); // Total score: 3, later start time

        // When
        List<String> summary = scoreboard.getSummary();

        // Then
        assertEquals(4, summary.size());
        assertEquals("Team C 3 - Team D 3", summary.get(0)); // Highest total score
        assertEquals("Team A 1 - Team B 2", summary.get(1)); // Same total score, earlier start time
        assertEquals("Team G 1 - Team H 2", summary.get(2)); // Same total score, later start time
        assertEquals("Team E 0 - Team F 1", summary.get(3)); // Lowest total score
    }
}