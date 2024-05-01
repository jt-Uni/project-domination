package test;
import core.Country;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountryTest {
    private Country country1;
    private Country country2;

    @Before
    public void setUp() {
        country1 = new Country("Country1", 1);
        country2 = new Country("Country2", 1);
    }

    @Test
    public void testGetName() {
        assertEquals("Country1", country1.getName());
        assertEquals("Country2", country2.getName());
    }

    @Test
    public void testAddNeighbor() {
        country1.addNeighbor(country2);
        assertTrue(country1.isNeighbor(country2));
    }

    @Test
    public void testStart() {
        country1.start(100);
        assertEquals(100, country1.getPossession());
        assertEquals(3, country1.getArmies());
        assertFalse(country1.isEmpty());
    }

    @Test
    public void testAttackDefend() {
        country1.start(100); // Start with initial setup
        country2.start(100); // Start with initial setup
        int initialArmiesCountry2 = country2.getArmies();
        country1.attack(country2);
        // Ensure armies are reduced or remain the same based on the dice roll
        assertTrue(country2.getArmies() <= initialArmiesCountry2);
    }
}
