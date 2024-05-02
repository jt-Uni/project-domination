/*
 * Rafik deboub
 * Sussex university
 * 2023/2024
 */





package core;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ScreenLogo extends JPanel{
    public BufferedImage Logo;
    
    public ScreenLogo(){
        setPreferredSize(new Dimension(700, 600));
        Logo = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        buffer();
        setVisible(true);
    }
    
    public void paint(Graphics g){
        g.drawImage(Logo, 0, 0, null);
    }
    
    public void buffer(){
        Image logo = new ImageIcon("./assets/screenStart.png").getImage();
        Graphics2D g2 = Logo.createGraphics();
        g2.drawImage(logo, 0, 0, 800, 600, null);
        repaint();
    }
}