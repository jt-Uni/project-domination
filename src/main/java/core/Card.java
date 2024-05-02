/*
 * Rafik deboub
 * Sussex university
 * 2023/2024
 */



package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;



/**
 * The `Card` class represents a card used in the World Conquest game.
 * Each card contains information about a country, unit type, and possession.
 */

public class Card {
    private String country;
    private int unit;
    private int possession;
    private BufferedImage card;
    private Dimension dim;
    private Rectangle border;

    private static final String FONT_PATH = "./assets/Urbana";
    private static final int BASE_WIDTH = 1920;
    private static final int BASE_HEIGHT = 1080;
    private static final int CARD_WIDTH = 220;
    private static final int CARD_HEIGHT = 320;
    private static final int INNER_MARGIN = 10;
    private static final Color BACKGROUND_COLOR = new Color(37, 17, 1);
    private static final Color INNER_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;



    /**
     * Constructs a new card for a given country, unit type, and possession.
     *
     * @param country the name of the country associated with the card
     * @param unit the unit type represented by the card (0: Infantry, 1: Cavalry, 2: Cannon, 3: Wild)
     * @param possession the ID of the player currently possessing the card
     */
    public Card(String country, int unit, int possession) {
        this.country = country;
        this.unit = unit;
        this.possession = possession;
        this.dim = Toolkit.getDefaultToolkit().getScreenSize();

        createCard();
    }


    /**
     * Creates the visual representation of the card, including text and unit image.
     */
    private void createCard() {
        int width = (int)((dim.getWidth() / BASE_WIDTH) * CARD_WIDTH);
        int height = (int)((dim.getHeight() / BASE_HEIGHT) * CARD_HEIGHT);
        card = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = card.createGraphics();

        try {
            // Draw background and border
            g2d.setColor(BACKGROUND_COLOR);
            g2d.fillRect(0, 0, width, height);

            g2d.setColor(INNER_COLOR);
            g2d.fillRect(INNER_MARGIN, INNER_MARGIN, width - 2 * INNER_MARGIN, height - 2 * INNER_MARGIN);

            // Draw text information
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font(FONT_PATH, Font.PLAIN, 24));
            g2d.drawString(country, (int)((dim.getWidth() / BASE_WIDTH) * 50), (int)((dim.getHeight() / BASE_HEIGHT) * 70));

            // Draw unit image
            String unitImagePath = getUnitImagePath(unit);
            if (unitImagePath != null) {
                Image cardUnit = new ImageIcon(unitImagePath).getImage();
                cardUnit = Card.resize(cardUnit, (int)((dim.getWidth() / BASE_WIDTH) * 190), (int)((dim.getWidth() / BASE_WIDTH) * 190));
                g2d.drawImage(cardUnit, (int)((dim.getWidth() / BASE_WIDTH) * 15), (int)((dim.getHeight() / BASE_HEIGHT) * 115), null);
            }
        } finally {
            g2d.dispose();
        }
    }



    /**
     * Retrieves the file path of the image corresponding to a given unit type.
     *
     * @param unit the unit type identifier (0, 1, 2, or 3)
     * @return the file path of the unit image or null if the unit type is invalid
     */
    private String getUnitImagePath(int unit) {
        return switch (unit) {
            case 0 -> "./assets/Infantry.jpg";
            case 1 -> "./assets/Cavalry.jpg";
            case 2 -> "./assets/Cannon.jpg";
            case 3 -> "./assets/Wild.jpg"; // Consider adding a consistent path prefix
            default -> null;
        };
    }



    /**
     * Resizes an image to the specified width and height.
     *
     * @param image the original image to resize
     * @param width the new width for the resized image
     * @param height the new height for the resized image
     * @return a BufferedImage representing the resized image
     */
    public static BufferedImage resize(Image image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    /**
     * Retrieves the country associated with this card.
     *
     * @return the name of the country
     */
    // Getter methods for accessing private fields
    public String getCountry() {
        return country;
    }


    /**
     * Retrieves the visual representation of this card.
     *
     * @return a BufferedImage containing the card's graphical content
     */
    public BufferedImage getCard() {
        return card;
    }


    /**
     * Retrieves the unit type represented by this card.
     *
     * @return an integer representing the unit type
     */
    public int getUnit() {
        return unit;
    }


    /**
     * Updates the possession status of this card.
     *
     * @param possession the ID of the player now possessing this card
     */
    public void heldBy(int possession) {
        this.possession = possession;
    }



    /**
     * Retrieves the ID of the player currently possessing this card.
     *
     * @return an integer representing the player's ID
     */
    public int getPossession() {
        return possession;
    }


    /**
     * Sets the border for the card.
     *
     * @param a the x-coordinate of the top-left corner
     * @param b the y-coordinate of the top-left corner
     * @param c the width of the border
     * @param d the height of the border
     */
    public void setBorder(int a, int b, int c, int d) {
        border = new Rectangle(a, b, c, d);
    }


    /**
     * Retrieves the border rectangle for this card.
     *
     * @return a Rectangle object defining the card's border
     */
    public Rectangle giveBorder() {
        return border;
    }
}
