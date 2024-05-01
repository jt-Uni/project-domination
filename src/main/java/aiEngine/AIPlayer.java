package aiEngine;

import core.*;
import java.awt.*;
import java.util.*;

public class AIPlayer extends Player {
    private String difficulty;
    private Random rand;

    public AIPlayer(String name, int playerId, Color color, String difficulty) {
        super(name, playerId, color);
        this.difficulty = difficulty;
        this.rand = new Random();
    }

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

    private void takeTurnEasy(WorldConquestGame game) {
        placeArmiesRandomly();
        attackIfAdvantageous();
        fortifyCountries();
        game.endTurn(); // End turn after actions
    }

    private void takeTurnHard(WorldConquestGame game) {
        placeArmiesStrategically();
        attackWithPlanning();
        fortifyCountries();
        game.endTurn(); // End turn after actions
    }

    private void takeTurnExtremelyHard(WorldConquestGame game) {
        placeArmiesForContinentControl();
        attackWithStrategy();
        fortifyCountries();
        game.endTurn(); // End turn after actions
    }

    private void placeArmiesRandomly() {
        int income = getIncome();
        Set<Country> ownCountries = new HashSet<>(getCountries());

        while (income > 0 && !ownCountries.isEmpty()) {
            Country target = new ArrayList<>(ownCountries).get(rand.nextInt(ownCountries.size()));
            target.gain(1);
            income--;
        }
    }

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

    private void placeArmiesStrategically() {
        int income = getIncome();
        Set<Country> strategicCountries = findStrategicCountries();

        for (Country country : strategicCountries) {
            while (income > 0) {
                country.gain(1);
                income--;
            }
        }
    }

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




    private Set<Country> findStrategicCountries() {
        Set<Country> strategic = new HashSet<>();
        for (Country country : getCountries()) {
            if (country.isStrategic()) strategic.add(country);
        }
        return strategic;
    }



    private Map<Integer, Integer> getContinentControl() {
        Map<Integer, Integer> controlMap = new HashMap<>();

        for (Country country : getCountries()) {
            controlMap.put(country.getContinent(), controlMap.getOrDefault(country.getContinent(), 0) + 1);
        }

        return controlMap;
    }

    private void fortifyCountry(Country from, Country to) {
        while (from.getArmies() > 1) {
            from.lose(1);
            to.gain(1);
        }
    }
}
