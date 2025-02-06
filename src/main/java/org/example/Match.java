package org.example;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Match{
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final AtomicInteger startTime;

    public Match(String homeTeam, String awayTeam, AtomicInteger startTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = startTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public AtomicInteger getStartTime() {
        return startTime;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeScore == match.homeScore && awayScore == match.awayScore && Objects.equals(homeTeam, match.homeTeam)
                && Objects.equals(awayTeam, match.awayTeam) && Objects.equals(startTime, match.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, startTime);
    }
}