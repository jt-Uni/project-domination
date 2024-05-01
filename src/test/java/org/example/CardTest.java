package org.example;

import core.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CardTest {

    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card("USA", 1, 1); // Sample data for initializing the Card object
    }

    @Test
    public void testCardCreation() {
        assertNotNull(card, "Card object should not be null");
    }

    @Test
    public void testCountry() {
        assertEquals("USA", card.getCountry(), "Country should match the initialized value");
    }

    @Test
    public void testUnit() {
        assertEquals(1, card.getUnit(), "Unit should match the initialized value");
    }

    @Test
    public void testPossession() {
        assertEquals(1, card.getPossession(), "Possession should match the initialized value");
    }

    @Test
    public void testGetCard() {
        BufferedImage cardImage = card.getCard();
        assertNotNull(cardImage, "Card image should not be null");
    }

    @Test
    public void testHeldBy() {
        card.heldBy(2);
        assertEquals(2, card.getPossession(), "Possession should be updated correctly");
    }

    @Test
    public void testBorder() {
        card.setBorder(10, 10, 100, 200);
        Rectangle border = card.giveBorder();
        assertNotNull(border, "Border should not be null");
        assertEquals(new Rectangle(10, 10, 100, 200), border, "Border should be set correctly");
    }
}
