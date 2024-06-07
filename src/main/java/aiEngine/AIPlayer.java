/**
 * Author: Rafik Deboub
 * Institution: Sussex University
 * Year: 2023/2024
 */

package aiEngine;

import core.*;
import java.awt.*;
import java.util.*;






/**
 * The AIPlayer class represents an AI-controlled player in the World Conquest game.
 * This class extends the Player class and provides different AI behaviors based on difficulty levels.
 */

public class AIPlayer extends Player {
    private String difficulty;
    private Random rand;



    /**
     * Constructs an AIPlayer with a specified name, player ID, color, and difficulty level.
     *
     * @param name       The name of the AI player.
     * @param playerId   The unique identifier for the player.
     * @param color      The color representing the player.
     * @param difficulty The difficulty level of the AI ("Easy", "Hard", or "Extremely Hard").
     */

    public AIPlayer(String name, int playerId, Color color, String difficulty) {
        super(name, playerId, color);
        this.difficulty = difficulty;
        this.rand = new Random();
    }


    /**
     * Takes a turn in the World Conquest game, performing actions based on the AI difficulty level.
     *
     * @param game The current World Conquest game instance.
     */

    public void takeTurn(WorldConquestGame game) {
        switch (difficulty) {
            case "Easy":
                takeTurnEasy(game);
                break;
            case "Hard":
                takeTurnHard(game);
                break;
            case "Extremely Hard":
                takeTurnExtremelyHard(game);
                break;
        }
    }



    /**
     * Executes a turn for an AI player on "Easy" difficulty.
     * @param game The WorldConquestGame instance managing the game state.
     */

    private void takeTurnEasy(WorldConquestGame game) {
        placeArmiesRandomly();
        attackIfAdvantageous();
        fortifyCountries();
        game.endTurn(); // End turn after actions
    }


    /**
     * Executes a turn for an AI player on "Hard" difficulty.
     * @param game The WorldConquestGame instance managing the game state.
     */

    private void takeTurnHard(WorldConquestGame game) {
        placeArmiesStrategically();
        attackWithPlanning();
        fortifyCountries();
        game.endTurn(); // End turn after actions
    }


    /**
     * Executes a turn for an AI player on "Extremely Hard" difficulty.
     * @param game The WorldConquestGame instance managing the game state.
     */

    private void takeTurnExtremelyHard(WorldConquestGame game) {
        placeArmiesForContinentControl();
        attackWithStrategy();
        fortifyCountries();
        game.endTurn(); // End turn after actions
    }



    /**
     * Places the AI player's armies randomly across its owned countries.
     */

    private void placeArmiesRandomly() {
        int income = getIncome();
        Set<Country> ownCountries = new HashSet<>(getCountries());

        while (income > 0 && !ownCountries.isEmpty()) {
            Country target = new ArrayList<>(ownCountries).get(rand.nextInt(ownCountries.size()));
            target.gain(1);
            income--;
        }
    }


    /**
     * Attacks neighboring countries if the AI player's country has an advantageous number of armies.
     */
    private void attackIfAdvantageous() {
        Set<Country> ownCountries = new HashSet<>(getCountries());

        for (Country country : ownCountries) {
            for (Country neighbor : country.getNeighbors()) {
                if (neighbor.getPossession() != getPlayer() && country.getArmies() > neighbor.getArmies()) {
                    country.attack(neighbor);
                    break;
                }
            }
        }
    }



    /**
     * Places the AI player's armies in a strategic manner, prioritizing certain countries.
     */

    private void placeArmiesStrategically() {
        int income = getIncome();
        Set<Country> strategicCountries = findStrategicCountries();

        for (Country country : strategicCountries) {
            if(income <= 0) break;
            country.gain(1);
                income--;

        }
    }


    /**
     * Plans attacks strategically across the AI player's owned countries.
     */

    private void attackWithPlanning() {
        Set<Country> ownCountries = new HashSet<>(getCountries());

        for (Country country : ownCountries) {
            for (Country neighbor : country.getNeighbors()) {
                if (neighbor.getPossession() != getPlayer() && country.getArmies() > neighbor.getArmies()) {
                    country.attack(neighbor);
                    // Check if conquered and update ownership:
                    if (neighbor.isEmpty()) {
                        neighbor.conqueredBy(getPlayer());
                        continue;// Skip further attacks on this territory
                    }
                }
            }
        }
    }




    /**
     * Places the AI player's armies to gain control of entire continents.
     */

    private void placeArmiesForContinentControl() {
        int income = getIncome();
        Set<Country> ownCountries = new HashSet<>(getCountries());

        Map<Integer, Integer> continentControl = getContinentControl();

        Set<Country> continentPriority = new HashSet<>();

        for (Country country : ownCountries) {
            if (continentControl.get(country.getContinent()) < continentControl.size()) {
                continentPriority.add(country);
            }
        }

        for (Country country : continentPriority) {
            while (income > 0) {
                country.gain(1);
                income--;
            }
        }
    }


    /**
     * Executes strategic attacks to conquer neighboring territories.
     */

    private void attackWithStrategy() {
        Set<Country> ownCountries = new HashSet<>(getCountries());

        for (Country country : ownCountries) {
            for (Country neighbor : country.getNeighbors()) {
                if (neighbor.getPossession() != getPlayer() && country.getArmies() > neighbor.getArmies()) {
                    country.attack(neighbor);

                    // Check if conquered and update ownership:
                    if (neighbor.isEmpty()) {
                        neighbor.conqueredBy(getPlayer());
                        continue;// Skip further attacks on this territory
                    }
                }
            }
        }
    }


    /**
     * Fortifies the AI player's countries, redistributing armies between them.
     */

    public void fortifyCountries() {
        Set<Country> ownCountries = new HashSet<>(getCountries());

        for (Country country : ownCountries) {
            for (Country neighbor : country.getNeighbors()) {
                if (country.getPossession() == getPlayer() && neighbor.getPossession() == getPlayer()) {
                    while (country.getArmies() > 1) {
                        country.lose(1);
                        neighbor.gain(1);
                    }
                }
            }
        }
    }




    /**
     * Finds countries considered strategic for the AI player.
     *
     * @return A set of strategic countries.
     */
    private Set<Country> findStrategicCountries() {
        Set<Country> strategic = new HashSet<>();
        for (Country country : getCountries()) {
            if (country.isStrategic()) strategic.add(country);
        }
        return strategic;
    }



    /**
     * Generates a map showing the level of control the AI player has over different continents.
     *
     * @return A map where the keys are continent identifiers and the values are the number of controlled countries.
     */
    private Map<Integer, Integer> getContinentControl() {
        Map<Integer, Integer> controlMap = new HashMap<>();

        for (Country country : getCountries()) {
            controlMap.put(country.getContinent(), controlMap.getOrDefault(country.getContinent(), 0) + 1);
        }

        return controlMap;
    }



    /**
     * Fortifies a target country by transferring armies from another country.
     *
     * @param from The country from which to transfer armies.
     * @param to   The country to fortify.
     */
    private void fortifyCountry(Country from, Country to) {
        while (from.getArmies() > 1) {
            from.lose(1);
            to.gain(1);
        }
    }
}
