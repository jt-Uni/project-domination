package core;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ScreenLogo extends JPanel{
    public BufferedImage Logo;
    
    public ScreenLogo(){ 
        setPreferredSize(new Dimension(700, 500));
        Logo = new BufferedImage(700, 600, BufferedImage.TYPE_INT_RGB);
        buffer();
        setVisible(true);
    }
    
    public void paint(Graphics g){
        g.drawImage(Logo, 0, 0, null);
    }
    
    public void buffer(){
        Image logo = new ImageIcon("./assets/LOGO.jpg").getImage();
        Graphics2D g2 = Logo.createGraphics();
        g2.drawImage(logo, 0, 0, 650, 550, null);
        repaint();
    }
}