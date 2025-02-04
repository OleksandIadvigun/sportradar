package org.example;

import java.time.Instant;

public interface IMatch {

    ITeam getHomeTeam();

    ITeam getAwayTeam();

    int getHomeScore();

    int getAwayScore();

    Instant getStartTime();

    void updateScore(int homeScore, int awayScore);
    int getTotalScore();
}
