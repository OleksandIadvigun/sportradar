package org.example;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Scoreboard implements IScoreboard {
    private final ConcurrentHashMap<String, IMatch> matches = new ConcurrentHashMap<>();

    private String generateKey(ITeam homeTeam, ITeam awayTeam) {
        return homeTeam.getName() + " vs " + awayTeam.getName();
    }

    public void startMatch(ITeam homeTeam, ITeam awayTeam) {
        if (homeTeam.equals(awayTeam)) {
            throw new RuntimeException("Home team and away team cannot be the same.");
        }
        String key = generateKey(homeTeam, awayTeam);
        matches.put(key, new Match(homeTeam, awayTeam));
    }

    public void updateScore(ITeam homeTeam, ITeam awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new RuntimeException("Scores cannot be negative.");
        }
        String key = generateKey(homeTeam, awayTeam);
        IMatch match = matches.get(key);
        if (match == null) {
            throw new RuntimeException("Match not found.");
        }
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(ITeam homeTeam, ITeam awayTeam) {
        String key = generateKey(homeTeam, awayTeam);
        IMatch match = matches.remove(key);
        if (match == null) {
            throw new RuntimeException("Match not found.");
        }
    }

    public List<String> getSummary() {
        return matches.values().stream()
                .sorted((m1, m2) -> Integer.compare(m2.getTotalScore(), m1.getTotalScore()))
                .map(IMatch::toString)
                .collect(Collectors.toList());
    }

}