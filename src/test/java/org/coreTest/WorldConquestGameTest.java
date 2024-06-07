package org.coreTest;

import core.Country;
import core.Player;
import core.WorldConquestGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;





/**
 * This class tests the functionality of the {@link WorldConquestGame} class.
 */
public class WorldConquestGameTest {

    private WorldConquestGame game;

    /**
     * Initializes a new {@link WorldConquestGame} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        game = new WorldConquestGame(); // Initialize the WorldConquestGame instance
    }

    /**
     * Tests the creation of the {@link WorldConquestGame} instance.
     */
    @Test
    public void testGameCreation() {
        assertNotNull(game, "Game object should not be null");
    }

    /**
     * Tests that the game initializes correctly.
     */
    @Test
    public void testGameInitialization() {
        assertNotNull(game.Countries, "Countries list should not be null");
        assertNotNull(game.Players, "Players list should not be null");
        assertFalse(game.Countries.isEmpty(), "Countries list should not be empty");
        assertFalse(game.Players.isEmpty(), "Players list should not be empty");
    }

    /**
     * Tests the player turn switching mechanism.
     */
    @Test
    public void testTurnSwitching() {
        int initialTurn = game.turn;

        game.endTurn(); // End current turn
        assertEquals((initialTurn + 1) % game.Players.size(), game.turn, "Turn should switch to the next player");

        // Repeat turn switching for multiple cycles
        for (int i = 0; i < game.Players.size() * 2; i++) {
            initialTurn = game.turn;
            game.endTurn();
            assertEquals((initialTurn + 1) % game.Players.size(), game.turn, "Turn should switch correctly");
        }
    }

    /**
     * Tests if cards are assigned correctly.
     */
    @Test
    public void testCardAssignment() {
        assertFalse(game.Cards.isEmpty(), "Cards list should not be empty");

        // Give a card to the first player and check
        game.Players.get(0).gotCard(game.Cards.remove(0));
        assertEquals(1, game.Players.get(0).getCardsN(), "Player should have 1 card");
    }

    /**
     * Tests the game's reward setup.
     */
    @Test
    public void testRewards() {
        assertFalse(game.Rewards.isEmpty(), "Rewards list should not be empty");

        int initialReward = game.Rewards.get(0);

        // Check if rewards progress as expected
        for (int i = 1; i < game.Rewards.size(); i++) {
            int nextReward = game.Rewards.get(i);
            assertTrue(nextReward > initialReward, "Rewards should progress incrementally");
            initialReward = nextReward;
        }
    }

    /**
     * Tests if a country is conquered correctly.
     */
    @Test
    public void testCountryConquest() {
        ArrayList<Country> countries = game.Countries;
        Player player1 = game.Players.get(0);
        Player player2 = game.Players.get(1);

        // Simulate an attack where player1 conquers a country from player2
        Country countryToConquer = countries.get(0); // Assuming player2 owns this

        countryToConquer.attack(player2.getCountries().get(0)); // Attack
        countryToConquer.conqueredBy(player1.getPlayer()); // Conquer

        assertTrue(player1.getCountries().contains(countryToConquer), "Country should belong to player1");
        assertFalse(player2.getCountries().contains(countryToConquer), "Country should no longer belong to player2");
    }

    /**
     * Tests for game completion conditions.
     */
    @Test
    public void testGameCompletion() {
        Player player1 = game.Players.get(0);

        // Check if all countries belong to player1
        assertEquals(48, player1.getCountries().size(), "Player1 should own all countries for a game victory");

        // Trigger game victory
        game.checkGameVictory();
    }


}
