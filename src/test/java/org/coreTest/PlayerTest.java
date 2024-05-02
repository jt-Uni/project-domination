package org.coreTest;

import core.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.util.List;





/**
 * Unit tests for the Player class, focusing on its attributes and methods.
 */
public class PlayerTest {

    private Player player;
    private Country country;
    private Card card;


    /**
     * Initializes a Player, Country, and Card for testing purposes.
     */
    @BeforeEach
    public void setUp() {
        player = new Player("Alice", 1, Color.RED); // Initialize with sample data
        country = new Country("USA", 1); // Create a sample country
        card = new Card("USA", 1, 1); // Create a sample card
    }


    /**
     * Tests if the Player object is created correctly and its attributes match the initialized values.
     */
    @Test
    public void testPlayerCreation() {
        assertNotNull(player, "Player object should not be null");
        assertEquals("Alice", player.getName(), "Player name should match the initialized value");
        assertEquals(1, player.getPlayer(), "Player number should match the initialized value");
    }


    /**
     * Tests the start method by checking if a player's countries are correctly initialized.
     */
    @Test
    public void testStart() {
        player.conquered(country); // Add a country to the player
        player.start(); // Start the player's countries
        assertEquals(1, country.getPossession(), "Country possession should match the player's ID");
    }


    /**
     * Tests if a country can be added to a player's list of conquered countries.
     */
    @Test
    public void testConquered() {
        player.conquered(country); // Add a country
        assertTrue(player.getCountries().contains(country), "Country should be added to the player's list");
    }


    /**
     * Tests if a country can be removed from a player's list of conquered countries.
     */
    @Test
    public void testLost() {
        player.conquered(country); // Add and then remove a country
        player.lost(country);
        assertFalse(player.getCountries().contains(country), "Country should be removed from the player's list");
    }


    /**
     * Tests if a player's income is correctly calculated based on its conquered countries.
     */
    @Test
    public void testGetIncome() {
        player.conquered(country); // Add a country to ensure income
        int income = player.getIncome();
        assertTrue(income >= 3, "Income should be at least 3");
    }


    /**
     * Tests if the player's color is correctly returned.
     */
    @Test
    public void testGetColor() {
        assertEquals(Color.RED, player.getColor(), "Player color should match the initialized value");
    }


    /**
     * Tests if cards can be added to and retrieved from a player's collection.
     */
    @Test
    public void testCards() {
        player.gotCard(card); // Add a card
        assertEquals(1, player.getCardsN(), "Player should have 1 card");
        assertTrue(player.getCards().contains(card), "Card should be in the player's collection");
    }


    /**
     * Tests if a card can be cashed and removed from a player's collection.
     */
    @Test
    public void testCashedCard() {
        player.gotCard(card); // Add and then cash a card
        player.cashedCard(card);
        assertFalse(player.getCards().contains(card), "Card should be removed from the player's collection");
    }


    /**
     * Tests if a player is able to cash a valid set of cards.
     */
    @Test
    public void testAbleToCash() {
        Card card1 = new Card("Country1", 0, 1);
        Card card2 = new Card("Country2", 1, 1);
        Card card3 = new Card("Country3", 2, 1);
        player.gotCard(card1);
        player.gotCard(card2);
        player.gotCard(card3);

        assertTrue(player.ableToCash(), "Player should be able to cash cards");
    }


    /**
     * Tests if a specific set of cards is cashable.
     */
    @Test
    public void testCashable() {
        Card card1 = new Card("Country1", 0, 1);
        Card card2 = new Card("Country2", 1, 1);
        Card card3 = new Card("Country3", 2, 1);

        assertTrue(player.cashable(card1, card2, card3), "Cards should form a cashable set");
    }



    /**
     * Tests if cashed cards are correctly returned.
     */
    @Test
    public void testReturnedCards() {
        player.gotCard(card); // Add and then cash a card
        player.cashedCard(card);

        List<Card> returnedCards = player.returnedCards();
        assertTrue(returnedCards.contains(card), "Returned cards should include the cashed card");
    }


}
