package org.example;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class MatchTest {

    private Match match;

    @Before
    public void setUp() {
        match = new Match(new Team("Team A"), new Team("Team B"));
    }

    @Test
    public void testConstructor() {
        // Given
        ITeam expectedHomeTeam = new Team("Team A");
        ITeam expectedAwayTeam = new Team("Team B");
        int expectedHomeScore = 0;
        int expectedAwayScore = 0;
        long expectedStartTime = Instant.now().getEpochSecond();

        // When
        Match match = new Match(expectedHomeTeam, expectedAwayTeam);

        // Then
        assertEquals(expectedHomeTeam, match.getHomeTeam());
        assertEquals(expectedAwayTeam, match.getAwayTeam());
        assertEquals(expectedHomeScore, match.getHomeScore());
        assertEquals(expectedAwayScore, match.getAwayScore());
        assertEquals(expectedStartTime, match.getStartTime().getEpochSecond(), 1);
    }

    @Test
    public void testUpdateScore() {
        // Given
        int newHomeScore = 3;
        int newAwayScore = 2;

        // When
        match.updateScore(newHomeScore, newAwayScore);

        // Then
        assertEquals(newHomeScore, match.getHomeScore());
        assertEquals(newAwayScore, match.getAwayScore());
    }

    @Test
    public void testGetTotalScore() {
        // Given
        int newHomeScore = 3;
        int newAwayScore = 2;
        match.updateScore(newHomeScore, newAwayScore);

        // When
        int totalScore = match.getTotalScore();

        // Then
        assertEquals(5, totalScore);
    }

    @Test
    public void testToString() {
        // Given
        int newHomeScore = 3;
        int newAwayScore = 2;
        match.updateScore(newHomeScore, newAwayScore);

        // When
        String result = match.toString();

        // Then
        assertEquals("Team A 3 - Team B 2", result);
    }
}