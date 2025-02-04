package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardConcurrencyTest {

    @Test
    public void testConcurrentAccess() throws InterruptedException {
        // Given
        IScoreboard scoreboard = new Scoreboard();
        int numberOfThreads = 100;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            int threadIndex = i;
            executorService.submit(() -> {
                try {
                    ITeam homeTeam = new Team("HomeTeam" + threadIndex);
                    ITeam awayTeam = new Team("AwayTeam" + threadIndex);
                    scoreboard.startMatch(homeTeam, awayTeam);
                    scoreboard.updateScore(homeTeam, awayTeam, threadIndex, threadIndex + 1);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // Then
        List<String> summary = scoreboard.getSummary();
        assertEquals(summary.size(), numberOfThreads);
    }
}