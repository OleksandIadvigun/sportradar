package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {

    private Match match;
    private String homeTeam;
    private String awayTeam;
    private AtomicInteger startTime;

    @BeforeEach
    public void setUp() {
        homeTeam = "Team A";
        awayTeam = "Team B";
        startTime = new AtomicInteger((int) Instant.now().getEpochSecond());
        match = new Match(homeTeam, awayTeam, startTime);
    }

    @Test
    public void constructor() {
        // Given
        String expectedHomeTeam = "Team A";
        String expectedAwayTeam = "Team B";
        int expectedHomeScore = 0;
        int expectedAwayScore = 0;
        AtomicInteger expectedStartTime = startTime;

        // When
        Match match = new Match(expectedHomeTeam, expectedAwayTeam, expectedStartTime);

        // Then
        assertEquals(expectedHomeTeam, match.getHomeTeam());
        assertEquals(expectedAwayTeam, match.getAwayTeam());
        assertEquals(expectedHomeScore, match.getHomeScore());
        assertEquals(expectedAwayScore, match.getAwayScore());
        assertEquals(expectedStartTime, match.getStartTime());
    }

    @Test
    public void getHomeTeam() {
        // Given
        String expectedHomeTeam = "Team A";

        // When
        String actualHomeTeam = match.getHomeTeam();

        // Then
        assertEquals(expectedHomeTeam, actualHomeTeam);
    }

    @Test
    public void getAwayTeam() {
        // Given
        String expectedAwayTeam = "Team B";

        // When
        String actualAwayTeam = match.getAwayTeam();

        // Then
        assertEquals(expectedAwayTeam, actualAwayTeam);
    }

    @Test
    public void getHomeScore() {
        // Given
        int expectedHomeScore = 0;

        // When
        int actualHomeScore = match.getHomeScore();

        // Then
        assertEquals(expectedHomeScore, actualHomeScore);
    }

    @Test
    public void getAwayScore() {
        // Given
        int expectedAwayScore = 0;

        // When
        int actualAwayScore = match.getAwayScore();

        // Then
        assertEquals(expectedAwayScore, actualAwayScore);
    }

    @Test
    public void getStartTime() {
        // Given
        AtomicInteger expectedStartTime = startTime;

        // When
        AtomicInteger actualStartTime = match.getStartTime();

        // Then
        assertEquals(expectedStartTime, actualStartTime);
    }

    @Test
    public void setHomeScore() {
        // Given
        int newHomeScore = 3;

        // When
        match.setHomeScore(newHomeScore);

        // Then
        assertEquals(newHomeScore, match.getHomeScore());
    }

    @Test
    public void setAwayScore() {
        // Given
        int newAwayScore = 2;

        // When
        match.setAwayScore(newAwayScore);

        // Then
        assertEquals(newAwayScore, match.getAwayScore());
    }

    @Test
    public void toStringTest() {
        // Given
        int newHomeScore = 3;
        int newAwayScore = 2;
        match.setHomeScore(newHomeScore);
        match.setAwayScore(newAwayScore);
        String expectedString = "Team A 3 - Team B 2";

        // When
        String actualString = match.toString();

        // Then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void equalsTest() {
        // Given
        Match match1 = new Match(homeTeam, awayTeam, startTime);
        Match match2 = new Match(homeTeam, awayTeam, startTime);

        // When & Then
        assertEquals(match1, match2);
    }

    @Test
    public void hashCodeTest() {
        // Given
        Match match1 = new Match(homeTeam, awayTeam, startTime);
        Match match2 = new Match(homeTeam, awayTeam, startTime);

        // When & Then
        assertEquals(match1.hashCode(), match2.hashCode());
    }
}