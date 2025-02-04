package org.example;

import java.util.List;

public interface IScoreboard {
    void startMatch(ITeam homeTeam, ITeam awayTeam);
    void updateScore(ITeam homeTeam, ITeam awayTeam, int homeScore, int awayScore);
    void finishMatch(ITeam homeTeam, ITeam awayTeam);
    List<String> getSummary();
}
