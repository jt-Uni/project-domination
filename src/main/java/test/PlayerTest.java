package test;

import core.Card;
import core.Country;
import core.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.util.List;  // Import List
import java.util.ArrayList;  // Also import ArrayList if not already present


public class PlayerTest {

    @Test
    public void testPlayerCreation() {
        Player player = new Player("TestPlayer", 1, Color.red);

        assertEquals("TestPlayer", player.getName(), "Player's name should be 'TestPlayer'.");
        assertEquals(1, player.getPlayer(), "Player's identifier should be 1.");
        assertEquals(Color.red, player.getColor(), "Player's color should be red.");
        assertEquals("red", player.getColorName(), "Color name should be 'red'.");
    }

    @Test
    public void testConqueredAndLost() {
        Player player = new Player("TestPlayer", 1, Color.blue);
        Country country1 = new Country("Country1", 0);
        Country country2 = new Country("Country2", 1);

        // Conquer country1
        player.conquered(country1);
        List<Country> playerCountries = player.getCountriesList();

        assertTrue(playerCountries.contains(country1), "Player's countries should include country1.");
        assertEquals(1, playerCountries.size(), "Player should have one country after conquering country1.");

        // Conquer country2
        player.conquered(country2);
        playerCountries = player.getCountriesList();

        assertTrue(playerCountries.contains(country2), "Player's countries should include country2.");
        assertEquals(2, playerCountries.size(), "Player should have two countries after conquering country2.");

        // Lose country1
        player.lost(country1);
        playerCountries = player.getCountriesList();

        assertFalse(playerCountries.contains(country1), "Player's countries should not include country1.");
        assertEquals(1, playerCountries.size(), "Player should have one country after losing country1.");
    }

    @Test
    public void testGetIncome() {
        Player player = new Player("TestPlayer", 1, Color.green);

        Country country1 = new Country("Country1", 0);
        Country country2 = new Country("Country2", 1);
        Country country3 = new Country("Country3", 2);

        player.conquered(country1);
        player.conquered(country2);
        player.conquered(country3);

        assertEquals(3, player.getIncome(), "Player's income should be 3 after conquering three countries.");
    }

    @Test
    public void testCardsManagement() {
        Player player = new Player("TestPlayer", 1, Color.black);

        Card card1 = new Card("Country1", 0, 0);
        Card card2 = new Card("Country2", 1, 0);

        player.gotCard(card1);
        player.gotCard(card2);

        assertEquals(2, player.getCardsN(), "Player should have 2 cards.");
        assertTrue(player.getCards().contains(card1), "Player's cards should contain card1.");
        assertTrue(player.getCards().contains(card2), "Player's cards should contain card2.");

        player.cashedCard(card1);
        assertEquals(1, player.getCardsN(), "Player should have 1 card after cashing card1.");
        assertFalse(player.getCards().contains(card1), "Player's cards should not contain card1.");
        assertTrue(player.getCards().contains(card2), "Player's cards should still contain card2.");
    }

    @Test
    public void testCashable() {
        Player player = new Player("TestPlayer", 1, Color.gray);

        Card card1 = new Card("Country1", 0, 0);
        Card card2 = new Card("Country2", 1, 0);
        Card card3 = new Card("Country3", 2, 0);

        assertTrue(player.cashable(card1, card2, card3), "The set should be cashable when it consists of one of each type.");
    }

    @Test
    public void testCash() {
        Player player = new Player("TestPlayer", 1, Color.green);

        Country country1 = new Country("Country1", 0);
        player.conquered(country1);

        Card card1 = new Card("Country1", 0, 0);
        Card card2 = new Card("Country2", 1, 0);
        Card card3 = new Card("Country3", 2, 0);

        ArrayList<Integer> rewards = new ArrayList<>();
        rewards.add(4); // Simple reward list

        int reward = player.cash(card1, card2, card3, rewards, 0);

        assertEquals(4, reward, "Reward should be 4 after cashing in the set.");
        assertEquals(2, country1.getArmies(), "Country1 should gain 2 armies from the card.");
    }
}
