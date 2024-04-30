package core;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ScreenLogo extends JPanel{
    public BufferedImage Logo;
    
    public ScreenLogo(){

        // Set the preferred size of the panel to 700x500 pixels
        setPreferredSize(new Dimension(750, 500));
        // Initialize a BufferedImage with dimensions 700x600 and RGB color model
        Logo = new BufferedImage(750, 600, BufferedImage.TYPE_INT_RGB);

        // Fill the BufferedImage with a specific image
        buffer();
        setVisible(true);// Make the panel visible
    }
    
    public void paint(Graphics g){
        g.drawImage(Logo, 0, 0, null);// Draw the BufferedImage 'Logo' at the top-left corner of the panel
    }
    
    public void buffer(){

        // Load an image from the specified path as an ImageIcon
        Image logo = new ImageIcon("./assets/LOGO.jpg").getImage();

        // Create a Graphics2D object from the BufferedImage to draw on it
        Graphics2D graphics2 = Logo.createGraphics();
        graphics2.drawImage(logo, 0, 0, 600, 550, null);
        repaint();// Request a repaint to display the new content
    }
}