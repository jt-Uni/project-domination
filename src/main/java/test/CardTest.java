package test;
import core.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    private Card card;

    @Before
    public void setUp() {
        // Initialize a Card object before each test
        card = new Card("Narnia", 1, 2);
    }

    @Test
    public void testGetCountry() {
        // Test if the country name is returned correctly
        assertEquals("Narnia", card.getCountry());
    }

    @Test
    public void testGetUnit() {
        // Test if the unit type is returned correctly
        assertEquals(1, card.getUnit());
    }

    @Test
    public void testGetPossession() {
        // Test if the possession status is returned correctly
        assertEquals(2, card.getPossession());
    }

    @Test
    public void testHeldBy() {
        // Test the heldBy method by updating the possession status
        card.heldBy(3);
        assertEquals(3, card.getPossession());
    }



    @Test
    public void testCardImageNotNull() {
        // Test if the card image is generated and is not null
        assertNotNull(card.getCard());
    }
}
