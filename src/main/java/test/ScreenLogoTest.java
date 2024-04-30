package test;
import core.ScreenLogo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScreenLogoTest {

    private ScreenLogo screenLogo;

    @Before
    public void setUp() {
        screenLogo = new ScreenLogo();
    }

    @Test
    public void testScreenLogoNotNull() {
        assertNotNull("ScreenLogo object should not be null", screenLogo);
    }

    @Test
    public void testLogoImageNotNull() {
        assertNotNull("Logo image should not be null after instantiation", screenLogo.Logo);
    }

    @Test
    public void testLogoImageSize() {
        // Assuming the size should be 700x600 as set in the constructor
        assertEquals("Logo width should be 700 pixels", 700, screenLogo.Logo.getWidth());
        assertEquals("Logo height should be 600 pixels", 600, screenLogo.Logo.getHeight());
    }
}
