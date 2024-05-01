package aiEngine;
import org.example.*;

import java.util.*;
import java.awt.*;

public class AIPlayer extends Player {
    private String difficulty;
    private Random rand;

    public AIPlayer(String name, int playerId, Color color, String difficulty) {
        super(name, playerId, color);
        this.difficulty = difficulty;
        this.rand = new Random();
    }

    public void takeTurn(RiskGame game) {
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

    private void takeTurnEasy(RiskGame game) {
        placeArmiesRandomly();
        attackIfAdvantageous();
    }

    private void takeTurnHard(RiskGame game) {
        placeArmiesStrategically();
        attackWithPlanning();
    }

    private void takeTurnExtremelyHard(RiskGame game) {
        placeArmiesForContinentControl();
        attackWithStrategy();
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
                    // Optionally handle attack results further
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
                    fortifyCountry(country, neighbor);
                }
            }
        }
    }

    private Set<Country> findStrategicCountries() {
        Set<Country> strategic = new HashSet<>();
        for (Country country : getCountries()) {
            if (country.isStrategic()) {
                strategic.add(country);
            }
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
