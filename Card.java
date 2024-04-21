import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.math.*;

public class Card{
    String country;
    int unit;
    int possession;
    BufferedImage card;
    Dimension dim;
    Rectangle border;
    
    public Card(String a, int b, int c){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        country = a;
        unit = b;
        possession = c;
        card = new BufferedImage((int)((dim.getWidth()/1920)*220), (int)((dim.getHeight()/1080)*320.0), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = card.createGraphics(); //draws the cards (adding the unit image, drawing on the country name)
        g2d.setColor(new Color(37, 17, 1));
        g2d.fillRect(0, 0, (int)((dim.getWidth()/1920)*220), (int)((dim.getHeight()/1080)*320.0));
        g2d.setColor(Color.white);
        g2d.fillRect(10, 10, (int)((dim.getWidth()/1920)*200), (int)((dim.getHeight()/1080)*300.0));
        g2d.setColor(Color.black);
        g2d.setFont(new Font("./assets/Urbana", Font.PLAIN, 24));
        g2d.drawString(country, (int)((dim.getWidth()/1920.0)*50), (int)((dim.getHeight()/1080.0)*70));
        Image cardUnit = new ImageIcon("./assets/Blank.jpg").getImage();
        if (unit == 0){
            cardUnit = new ImageIcon("./assets/Infantry.jpg").getImage();
            cardUnit = resize(cardUnit, (int)((dim.getWidth()/1920.0)*190), (int)((dim.getWidth()/1920.0)*190));
        }
        if (unit == 1){
            cardUnit = new ImageIcon("./assets/Cavalry.jpg").getImage();
            cardUnit = resize(cardUnit, (int)((dim.getWidth()/1920.0)*190), (int)((dim.getWidth()/1920.0)*190));
        }
        if (unit == 2){
            cardUnit = new ImageIcon("./assets/Cannon.jpg").getImage();
            cardUnit = resize(cardUnit, (int)((dim.getWidth()/1920.0)*190), (int)((dim.getWidth()/1920.0)*190));
        }
        if (unit == 3){
            cardUnit = new ImageIcon("./assets/Wild.jpg").getImage();
            cardUnit = resize(cardUnit, (int)((dim.getWidth()/1920.0)*190), (int)((dim.getWidth()/1920.0)*190));
        }
        g2d.drawImage(cardUnit, (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*115), null);
    }

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
