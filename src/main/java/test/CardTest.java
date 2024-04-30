package test;

import core.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardTest {

    @Test
    public void testCardCreation() {
        Card card = new Card("Country1", 1, 0);

        assertEquals("Country1", card.getCountry(), "Card should be for 'Country1'.");
        assertEquals(1, card.getUnit(), "Card should represent 1 unit.");
        assertEquals(0, card.getPossession(), "Initial possession should be 0.");
    }

    @Test
    public void testGraphicsInitialization() {
        Card card = new Card("Country1", 1, 0);
        BufferedImage cardImage = card.getCard();

        assertNotNull(cardImage, "Card image should be initialized.");
        assertEquals(BufferedImage.TYPE_INT_RGB, cardImage.getType(), "Card image should be of type RGB.");
    }

    @Test
    public void testCardBorder() {
        Card card = new Card("Country1", 1, 0);

        card.setBorder(10, 20, 30, 40);
        Rectangle border = card.giveBorder();

        assertNotNull(border, "Card border should be initialized.");
        assertEquals(10, border.x, "Border x should be 10.");
        assertEquals(20, border.y, "Border y should be 20.");
        assertEquals(30, border.width, "Border width should be 30.");
        assertEquals(40, border.height, "Border height should be 40.");
    }

    @Test
    public void testHeldByAndPossession() {
        Card card = new Card("Country1", 1, 0);

        card.heldBy(1);
        assertEquals(1, card.getPossession(), "Card should be held by player 1.");

        card.heldBy(2);
        assertEquals(2, card.getPossession(), "Card should be held by player 2.");
    }

    @Test
    public void testResize() {
        BufferedImage originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        BufferedImage resizedImage = Card.resize(originalImage, 50, 50);

        assertNotNull(resizedImage, "Resized image should be created.");
        assertEquals(50, resizedImage.getWidth(), "Resized image width should be 50.");
        assertEquals(50, resizedImage.getHeight(), "Resized image height should be 50.");
    }
}
