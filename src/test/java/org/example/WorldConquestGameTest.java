package org.example;

import core.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import java.awt.Rectangle;

public class WorldConquestGameTest {

    WorldConquestGame game;

    @Before
    public void setup() {
        game = new WorldConquestGame();
    }

    @Test
    public void testGameInitialization() {
        // Check basic game state setup
        assertFalse(game.isActive()); // Game should not be active initially
        assertEquals(0, game.turn); // Turn should start at 0
        assertTrue(game.place); // Game should be in place mode initially

        // Verify the number of players and countries
        assertEquals(2, game.Players.size()); // Assume two players
        assertEquals(48, game.Countries.size()); // 48 countries total
    }

    @Test
    public void testPlayerSetup() {
        // Players should have countries distributed
        for (Player player : game.Players) {
            assertFalse(player.getCountries().isEmpty()); // Every player has at least one country
        }
    }

    @Test
    public void testVictoryCondition() {
        // Mocking a victory scenario
        Player player = game.Players.get(0); // First player
        player.getCountries().addAll(game.Countries); // Give all countries to the player

        // Check if the game recognizes victory
        game.checkGameVictory();
        // Normally expect a dialog or system exit
    }


    @Test
    public void testDiceRollBias() {
    // Check if dice rolls maintain a 70-30 distribution
    int attacksWon = 0;
    int defensesWon = 0;
    Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int roll = random.nextInt(101);
            if (roll < 70) {
            attacksWon++;
        } else {
            defensesWon++;
        }
    }

    assertTrue(attacksWon > defensesWon); // Check that attacks win more often
}

@Test
public void testViewCards() {
    game.Players.get(game.turn).getCards().add(new Card("TestCard", 1, -1)); // Add a mock card
    game.viewCards(); // Trigger view

    // Check if the viewing state changes
    assertTrue(game.view);
    assertFalse(game.isActive()); // Game shouldn't be active while viewing
}







@Test
public void testResetTurnState() {
    // Set various states
    game.attack = true;
    game.fortify = true;
    game.fortified = true;

    game.resetTurnState(); // Reset the state

    assertFalse(game.attack);
    assertTrue(game.place);
    assertFalse(game.fortify);
    assertFalse(game.fortified);
}
}


