package org.example;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard implements IScoreboard {
    private List<IMatch> matches = new ArrayList<>();

    public void startMatch(ITeam homeTeam, ITeam awayTeam) {
        // todo
    }

    public void updateScore(ITeam homeTeam, ITeam awayTeam, int homeScore, int awayScore) {
        // todo
    }

    public void finishMatch(ITeam homeTeam, ITeam awayTeam) {
        // todo
    }

    public List<String> getSummary() {
        return null; // todo
    }
}