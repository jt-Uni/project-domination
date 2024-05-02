/*
 * Rafik deboub
 * Sussex university
 * 2023/2024
 */


package core;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;




/**
 * ScreenLogo is a JPanel that displays a graphical logo for the game's start screen.
 */
public class ScreenLogo extends JPanel{
    public BufferedImage Logo;


    /**
     * Constructs a ScreenLogo panel.
     * Initializes the logo and its dimensions, and displays it.
     */
    public ScreenLogo(){
        setPreferredSize(new Dimension(700, 600));
        Logo = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        buffer();
        setVisible(true);
    }



    /**
     * Paints the logo onto the panel.
     *
     * @param g the graphics context to use for drawing
     */
    public void paint(Graphics g){
        g.drawImage(Logo, 0, 0, null);
    }



    /**
     * Buffers the logo image from a file and redraws it on the panel.
     */
    public void buffer(){
        Image logo = new ImageIcon("./assets/screenStart.png").getImage();
        Graphics2D g2 = Logo.createGraphics();
        g2.drawImage(logo, 0, 0, 800, 600, null);
        repaint();
    }
}