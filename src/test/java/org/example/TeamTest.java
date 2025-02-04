package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    @Test
    public void testConstructorAndGetName() {
        // Given
        String expectedName = "Team A";

        // When
        ITeam team = new Team(expectedName);

        // Then
        assertEquals(expectedName, team.getName());
    }

    @Test
    public void testToString() {
        // Given
        String expectedName = "Team A";
        ITeam team = new Team(expectedName);

        // When
        String result = team.toString();

        // Then
        assertEquals(expectedName, result);
    }
}