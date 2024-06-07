package aiEngineTest;

import aiEngine.AIPlayer;
import core.Country;
import core.WorldConquestGame;
import core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;







/**
 * This class contains unit tests for AIPlayer functionality.
 */
public class AIPlayerTest {

    private WorldConquestGame mockGame;
    private AIPlayer easyAI;
    private AIPlayer hardAI;
    private AIPlayer extremelyHardAI;
    private ArrayList<Country> mockCountries;


    /**
     * Sets up mock objects and test scenarios before each test run.
     */
    @BeforeEach
    public void setup() {
        // Initialize mock countries
        mockCountries = new ArrayList<>();
        mockCountries.add(new Country("TestCountry1", 0));
        mockCountries.add(new Country("TestCountry2", 0));
        mockCountries.get(0).addNeighbor(mockCountries.get(1)); // Set neighbors

        // Create a mock game
        mockGame = new WorldConquestGame();
        mockGame.Countries = mockCountries;

        // Initialize AI players
        easyAI = new AIPlayer("Easy AI", 1, Color.BLUE, "Easy");
        hardAI = new AIPlayer("Hard AI", 2, Color.RED, "Hard");
        extremelyHardAI = new AIPlayer("Hardcore AI", 3, Color.GREEN, "Extremely Hard");
    }




    /**
     * Tests the AIPlayer's ability to take turns at each difficulty level.
     */
    @Test
    public void testTakeTurn() {
        // Test taking a turn with each AI difficulty
        easyAI.takeTurn(mockGame);
        assertTrue(mockGame.end, "Easy AI should end its turn.");

        hardAI.takeTurn(mockGame);
        assertTrue(mockGame.end, "Hard AI should end its turn.");

        extremelyHardAI.takeTurn(mockGame);
        assertTrue(mockGame.end, "Extremely Hard AI should end its turn.");
    }





    /**
     * Tests the specific actions of the Easy AI.
     */
    @Test
    public void testEasyAIActions() {
        // Test actions specific to the Easy AI
        easyAI.takeTurn(mockGame);

        assertTrue(mockCountries.get(0).getArmies() > 0, "Easy AI should place armies.");
        assertTrue(mockGame.end, "Easy AI should end its turn.");
    }



    /**
     * Tests the specific actions of the Hard AI.
     */
    @Test
    public void testHardAIActions() {
        // Test actions specific to the Hard AI
        hardAI.takeTurn(mockGame);

        assertTrue(mockCountries.get(0).getArmies() > 0, "Hard AI should place armies.");
        assertTrue(mockGame.end, "Hard AI should end its turn.");
    }



    /**
     * Tests the specific actions of the Extremely Hard AI.
     */
    @Test
    public void testExtremelyHardAIActions() {
        // Test actions specific to the Extremely Hard AI
        extremelyHardAI.takeTurn(mockGame);

        assertTrue(mockCountries.get(0).getArmies() > 0, "Extremely Hard AI should place armies.");
        assertTrue(mockGame.end, "Extremely Hard AI should end its turn.");
    }




    /**
     * Tests if the Easy AI places armies randomly.
     */
    @Test
    public void testPlaceArmiesRandomly() {
        // Check if Easy AI places armies randomly
        easyAI.takeTurn(mockGame);
        assertTrue(mockCountries.get(0).getArmies() > 0, "Easy AI should deploy armies.");
    }




    /**
     * Tests if the Hard AI plans attacks effectively.
     */
    @Test
    public void testHardAIPlanning() {
        // Check if Hard AI plans attacks effectively
        hardAI.takeTurn(mockGame);
        assertTrue(mockCountries.get(1).getPossession() == 2, "Hard AI should conduct planned attacks.");
    }




    /**
     * Tests if the Extremely Hard AI focuses on continent control.
     */
    @Test
    public void testContinentControl() {
        // Check if Extremely Hard AI focuses on continent control
        extremelyHardAI.takeTurn(mockGame);
        assertTrue(mockCountries.get(1).getPossession() == 3, "Extremely Hard AI should work towards continent control.");
    }
}
