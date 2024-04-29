package org.example;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() {
        player = new Player("John Doe", 1, Color.red);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("John Doe", player.getName());
        assertEquals(1, player.getPlayer());
        assertEquals(Color.red, player.getColor());
        assertEquals("red", player.getColorName());
        assertEquals(0, player.getCountries());
    }

    @Test
    public void testConqueredAndLost() {
        Country country1 = new Country("Country1", 0);
        Country country2 = new Country("Country2", 1);

        player.conquered(country1);
        assertEquals(1, player.getCountries());  // Corrected to use assertEquals to check the number of countries
        assertTrue(player.countries.contains(country1));

        player.conquered(country2);
        assertEquals(2, player.getCountries());  // Corrected to use assertEquals
        assertTrue(player.countries.contains(country2));

        player.lost(country1);
        assertEquals(1, player.getCountries());  // Corrected to use assertEquals
        assertFalse(player.countries.contains(country1));
    }

    @Test
    public void testGetIncome() {
        for (int i = 0; i < 9; i++) {
            player.conquered(new Country("Country" + i, i % 5));
        }
        assertEquals(3, player.getIncome());  // Assuming basic income rules without continent bonus

        player.conquered(new Country("Country10", 5));
        assertEquals(3, player.getIncome());  // With 10 countries, the income should be 10 / 3, rounded down
    }
}
