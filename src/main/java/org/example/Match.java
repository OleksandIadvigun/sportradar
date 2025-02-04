package org.example;

import java.time.Instant;
import java.util.Objects;

public class Match implements IMatch {
    private final ITeam homeTeam;
    private final ITeam awayTeam;
    private int homeScore;
    private int awayScore;
    private final Instant startTime;

    public Match(ITeam homeTeam, ITeam awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = Instant.now();
    }

    public Match(ITeam homeTeam, ITeam awayTeam, Instant startTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = startTime;
    }

    public ITeam getHomeTeam() {
        return homeTeam;
    }

    public ITeam getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
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