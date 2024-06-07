package org.coreTest;

import core.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.concurrent.TimeUnit;





/**
 * This class tests the functionality of the {@link StartScreen} class.
 */
public class StartScreenTest {

    private StartScreen startScreen;



    /**
     * Initializes a new {@link StartScreen} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        startScreen = new StartScreen(); // Initialize the StartScreen instance
    }


    /**
     * Tests the creation of the {@link StartScreen} instance.
     */
    @Test
    public void testStartScreenCreation() {
        assertNotNull(startScreen, "StartScreen object should not be null");
        assertTrue(startScreen.isVisible(), "StartScreen window should be visible");
    }


    /**
     * Tests the player configuration setup on the {@link StartScreen}.
     */
    @Test
    public void testPlayersConfiguration() {
        // Setup configurations directly
        JTextField[] playerNames = startScreen.playerNames;
        JComboBox<String>[] playerTypes = startScreen.playerTypes;
        JComboBox<String>[] colorCombos = startScreen.colorCombos;
        JComboBox<String>[] difficultyCombos = startScreen.difficultyCombos;

        for (int i = 0; i < 6; i++) {
            playerNames[i].setText("Player " + (i + 1));
            playerTypes[i].setSelectedIndex(i % 2); // Alternate between Human and AI
            colorCombos[i].setSelectedIndex((i % 6) + 1); // Assign valid colors
            if (playerTypes[i].getSelectedIndex() == 1) {
                difficultyCombos[i].setSelectedIndex(0); // Set difficulty for AI players
            }
        }

        // Manually trigger the setup
        startScreen.setupPlayers();

        ArrayList<Player> users = startScreen.getPlayers();
        assertEquals(users.size() >= 2, true, "There should be at least two players");

        // Check configuration correctness
        Set<String> names = new HashSet<>();
        Set<Color> colors = new HashSet<>();

        for (Player player : users) {
            assertFalse(names.contains(player.getName()), "No duplicate player names");
            assertFalse(colors.contains(player.getColor()), "No duplicate player colors");

            names.add(player.getName());
            colors.add(player.getColor());
        }
    }



    /**
     * Tests if the game starts successfully after pressing the start button.
     */

    @Test
    public void testStarted() {
        // Create the StartScreen instance
        startScreen = new StartScreen();

        // Simulate button press
        ActionEvent startEvent = new ActionEvent(new JButton("Start"), 1, "Start");
        startScreen.actionPerformed(startEvent);

        // Wait until the game state is ready
        waitForStart();

        assertEquals(startScreen.started(), true, "Game should be started after pressing 'Start'");
    }




    private void waitForStart() {
        while (!startScreen.started()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100); // Add a small delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }
}
