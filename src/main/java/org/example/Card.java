package org.example;
import java.awt.*;
import java.awt.image.*;

public class Card {
    private String country;
    private int unit;
    private int possession;
    private BufferedImage card;
    private Dimension screenDimension;
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

    public Card(String country, int unit, int possession) {
        this.country = country;
        this.unit = unit;
        this.possession = possession;
        this.screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        initializeCard();
    }

    private void initializeCard() {
        this.card = createCardImage();
        drawCardGraphics();
    }

    private BufferedImage createCardImage() {
        int width = (int) (screenDimension.getWidth() / BASE_WIDTH * CARD_WIDTH);
        int height = (int) (screenDimension.getHeight() / BASE_HEIGHT * CARD_HEIGHT);
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private void drawCardGraphics() {
        Graphics2D g2d = this.card.createGraphics();
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0, 0, this.card.getWidth(), this.card.getHeight());
        g2d.setColor(INNER_COLOR);
        g2d.fillRect(INNER_MARGIN, INNER_MARGIN, this.card.getWidth() - 2 * INNER_MARGIN, this.card.getHeight() - 2 * INNER_MARGIN);
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font(FONT_PATH, Font.PLAIN, 20)); // Adjust font size as needed
        g2d.drawString(country + " - Units: " + unit, INNER_MARGIN, 30); // Example placement
        g2d.dispose();
    }

    // Getter methods for accessing private fields
    public String getCountry(){
        return country;
    }

    public BufferedImage getCard(){
        return card;
    }

    public int getUnit(){
        return unit;
    }

    public void heldBy(int a){
        possession = a;
    }

    public int getPossession(){
        return possession;
    }

    public void setBorder(int a, int b, int c, int d){
        border = new Rectangle(a, b, c, d);
    }

    public Rectangle giveBorder(){
        return border;
    }

    public static BufferedImage resize(Image image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}
