package org.example;

import core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CountryTest {

    private Country country;
    private Country neighborCountry;

    @BeforeEach
    public void setUp() {
        country = new Country("USA", 1); // Initialize with sample data
        neighborCountry = new Country("Canada", 1); // Create a neighboring country
    }

    @Test
    public void testCountryCreation() {
        assertNotNull(country, "Country object should not be null");
        assertEquals("USA", country.getName(), "Country name should match the initialized value");
    }

    @Test
    public void testStart() {
        country.start(2);
        assertEquals(2, country.getPossession(), "Possession should match the initialized value");
        assertEquals(5, country.getArmies(), "Armies should start at 5");
    }

    @Test
    public void testAddNeighbor() {
        country.addNeighbor(neighborCountry);
        assertTrue(country.isNeighbor(neighborCountry), "Neighbor should be added correctly");
    }

    @Test
    public void testAddNeighborByName() {
        ArrayList<Country> allCountries = new ArrayList<>(List.of(neighborCountry));
        country.addNeighbor(allCountries, "Canada");
        assertTrue(country.isNeighbor(neighborCountry), "Neighbor should be added correctly by name");
    }

    @Test
    public void testRemoveNeighborByName() {
        ArrayList<Country> allCountries = new ArrayList<>(List.of(neighborCountry));
        country.addNeighbor(allCountries, "Canada");
        country.removeNeighbor(allCountries, "Canada");
        assertFalse(country.isNeighbor(neighborCountry), "Neighbor should be removed correctly by name");
    }

    @Test
    public void testSetAndGetBorder() {
        country.setBord(10, 10, 100, 200);
        Rectangle border = country.giveBorder();
        assertEquals(new Rectangle(10, 10, 100, 200), border, "Border should be set correctly");
    }

    @Test
    public void testAttackAndDefend() {
        country.start(1);
        neighborCountry.start(1);

        country.attack(neighborCountry);
        // Check that one of the countries lost an army
        assertEquals(country.getArmies() < 5 || neighborCountry.getArmies() < 5, true, "One of the countries should lose an army");
    }

    @Test
    public void testConqueredBy() {
        country.conqueredBy(2);
        assertEquals(2, country.getPossession(), "Country should be conquered by the new possession");
    }

    @Test
    public void testIsStrategic() {
        country.addNeighbor(neighborCountry);
        Country anotherNeighbor = new Country("Mexico", 2);
        country.addNeighbor(anotherNeighbor);

        assertEquals(country.isStrategic(), false, "Country with more than two neighbors should be strategic");
    }


}
