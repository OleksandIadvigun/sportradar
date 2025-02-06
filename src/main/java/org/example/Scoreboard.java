package org.example;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Scoreboard {
    private final ConcurrentHashMap<String, Match> matches = new ConcurrentHashMap<>();

    private String generateKey(String homeTeam, String awayTeam) {
        return homeTeam + " vs " + awayTeam;
    }

    public void startMatch(String homeTeam, String awayTeam, Instant startTime) {
        if (homeTeam.equals(awayTeam) || homeTeam.isEmpty() || awayTeam.isEmpty() || matches.containsKey(generateKey(homeTeam, awayTeam))) {
            throw new IllegalArgumentException("Home team and away team cannot be the same.");
        }
        String key = generateKey(homeTeam, awayTeam);
        matches.put(key, new Match(homeTeam, awayTeam, startTime));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }
        String key = generateKey(homeTeam, awayTeam);
        Match match = matches.get(key);
        if (match == null) {
            throw new NoSuchElementException("Match not found.");
        }
        updateScore(match, homeScore, awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        String key = generateKey(homeTeam, awayTeam);
        Match match = matches.remove(key);
        if (match == null) {
            throw new NoSuchElementException("Match not found.");
        }
    }

    public List<String> getSummary() {
        return matches.values().stream()
                .sorted((m1, m2) -> {
                    int scoreComparison = Integer.compare(
                            m2.getHomeScore() + m2.getAwayScore(),
                            m1.getHomeScore() + m1.getAwayScore());
                    if (scoreComparison != 0) {
                        return scoreComparison;
                    }
                    return Long.compare(m1.getStartTime().getEpochSecond(), m2.getStartTime().getEpochSecond());
                })
                .map(Match::toString)
                .collect(Collectors.toList());
    }

    private void updateScore(Match match, int homeScore, int awayScore) {
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }

}