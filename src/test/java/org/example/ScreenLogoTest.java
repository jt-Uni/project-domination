package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import core.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;

public class ScreenLogoTest {

    private ScreenLogo screenLogo;

    @BeforeEach
    public void setUp() {
        screenLogo = new ScreenLogo(); // Initialize the ScreenLogo instance
    }

    @Test
    public void testScreenLogoCreation() {
        assertNotNull(screenLogo, "ScreenLogo object should not be null");
    }

    @Test
    public void testBuffer() {
        BufferedImage logo = screenLogo.Logo;
        assertNotNull(logo, "Logo buffer should not be null");
        assertEquals(700, logo.getWidth(), "Logo width should match the initialized dimension");
        assertEquals(600, logo.getHeight(), "Logo height should match the initialized dimension");
    }

    @Test
    public void testPaint() {
        BufferedImage bufferImage = new BufferedImage(700, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferImage.getGraphics();

        screenLogo.paint(g); // Call paint method directly
        assertNotNull(bufferImage, "Painted image should not be null");

        // Optionally, you can add checks to ensure the painted content is accurate.
        // This can include pixel checks or comparing to an expected image.
    }


}
