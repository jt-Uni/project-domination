package org.coreTest;

import core.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;






/**
 * Unit tests for the Card class.
 */
public class CardTest {

    private Card card;


    /**
     * Sets up a sample card object before each test.
     */
    @BeforeEach
    public void setUp() {
        card = new Card("USA", 1, 1); // Sample data for initializing the Card object
    }


    /**
     * Tests that the Card object is created successfully.
     */
    @Test
    public void testCardCreation() {
        assertNotNull(card, "Card object should not be null");
    }


    /**
     * Tests that the country associated with the Card is retrieved correctly.
     */
    @Test
    public void testCountry() {
        assertEquals("USA", card.getCountry(), "Country should match the initialized value");
    }


    /**
     * Tests that the unit associated with the Card is retrieved correctly.
     */
    @Test
    public void testUnit() {
        assertEquals(1, card.getUnit(), "Unit should match the initialized value");
    }


    /**
     * Tests that the possession associated with the Card is retrieved correctly.
     */
    @Test
    public void testPossession() {
        assertEquals(1, card.getPossession(), "Possession should match the initialized value");
    }


    /**
     * Tests that the card image is retrieved correctly.
     */
    @Test
    public void testGetCard() {
        BufferedImage cardImage = card.getCard();
        assertNotNull(cardImage, "Card image should not be null");
    }


    /**
     * Tests that the possession of the Card is updated correctly.
     */
    @Test
    public void testHeldBy() {
        card.heldBy(2);
        assertEquals(2, card.getPossession(), "Possession should be updated correctly");
    }


    /**
     * Tests that the border of the Card is set and retrieved correctly.
     */
    @Test
    public void testBorder() {
        card.setBorder(10, 10, 100, 200);
        Rectangle border = card.giveBorder();
        assertNotNull(border, "Border should not be null");
        assertEquals(new Rectangle(10, 10, 100, 200), border, "Border should be set correctly");
    }
}
