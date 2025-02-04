package org.example;

import java.time.Instant;

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
}