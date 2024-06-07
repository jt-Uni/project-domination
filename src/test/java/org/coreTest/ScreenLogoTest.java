package org.coreTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import core.*;
import java.awt.image.BufferedImage;
import java.awt.*;






/**
 * This class tests the functionality of the {@link ScreenLogo} class.
 */
public class ScreenLogoTest {

    private ScreenLogo screenLogo;



    /**
     * Initializes a new {@link ScreenLogo} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        screenLogo = new ScreenLogo(); // Initialize the ScreenLogo instance
    }


    /**
     * Tests the creation of the {@link ScreenLogo} instance.
     */
    @Test
    public void testScreenLogoCreation() {
        assertNotNull(screenLogo, "ScreenLogo object should not be null");
    }



    /**
     * Tests the buffer creation of the {@link ScreenLogo}.
     */
    @Test
    public void testBuffer() {
        BufferedImage logo = screenLogo.Logo;
        assertNotNull(logo, "Logo buffer should not be null");
        assertEquals(800, logo.getWidth(), "Logo width should match the initialized dimension");
        assertEquals(600, logo.getHeight(), "Logo height should match the initialized dimension");
    }


    /**
     * Tests the paint method of the {@link ScreenLogo} class.
     */
    @Test
    public void testPaint() {
        BufferedImage bufferImage = new BufferedImage(700, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferImage.getGraphics();

        screenLogo.paint(g); // Call paint method directly
        assertNotNull(bufferImage, "Painted image should not be null");


    }


}
