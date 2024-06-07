/*
 * Rafik deboub
 * Sussex university
 * 2023/2024
 */





package core;
import java.awt.*;
import java.util.*;


/**
 * The `Country` class represents a country in the World Conquest game.
 * It includes information about its name, armies, possession status, continent, neighbors, and visual representation.
 */

public class Country{ //everything is pretty clear
    public void setArmies(int armies) {
        this.armies = armies;
    }

    int armies, possession, continent;
    boolean empty;
    String country;
    Random diceRoll;
    Set<Country> neighbors;
    Rectangle borders;
    Image selected;




    /**
     * Constructs a new country with a specified name and continent.
     *
     * @param a the name of the country
     * @param b the ID of the continent to which the country belongs
     */
    public Country(String a, int b){
        country = a;
        continent = b;
        diceRoll = new Random();
        neighbors = new HashSet<>();
        empty = true;
    }


    /**
     * Sets the visual representation of the country.
     *
     * @param a the image representing the country
     */
    public void selectedImage(Image a){
        selected = a;
    }


    /**
     * Initializes the country's possession and armies.
     *
     * @param a the ID of the player possessing the country
     */
    public void start(int a){
        possession = a;
        armies = 5;
        empty = false;
    }


    /**
     * Adds a neighboring country.
     *
     * @param a the country to add as a neighbor
     */
    public void addNeighbor(Country a){
        neighbors.add(a);
    }


    /**
     * Adds a neighboring country from a list of countries.
     *
     * @param a a list of available countries
     * @param b the name of the country to add as a neighbor
     */
    public void addNeighbor(ArrayList<Country> a, String b){
        for (int c = 0; c < 42; c++){
            if (!neighbors.contains(a.get(c)) && b.equals(a.get(c).getName())){
                neighbors.add(a.get(c));
                break;
            }
        }
    }


    /**
     * Removes a neighboring country from a list of countries.
     *
     * @param a a list of available countries
     * @param b the name of the country to remove as a neighbor
     */
    public void removeNeighbor(ArrayList<Country> a, String b){
        for (int c = 0; c < 42; c++){
            if (neighbors.contains(a.get(c)) && b.equals(a.get(c).getName())){
                neighbors.remove(a.get(c));
                break;
            }
        }
    }



    /**
     * Checks if a given country is a neighbor.
     *
     * @param a the country to check
     * @return true if the country is a neighbor, false otherwise
     */
    public boolean isNeighbor(Country a){
        for (Country neighbor : neighbors) {
            if (neighbor == a) {
                return true;
            }
        }
        return false;
    }



    /**
     * Retrieves the visual representation of the country.
     *
     * @return an Image representing the country
     */
    public Image getSelected(){
        return selected;
    }



    /**
     * Retrieves the name of the country.
     *
     * @return the country's name
     */
    public String getName(){
        return country;
    }


    /**
     * Retrieves the continent ID of the country.
     *
     * @return the continent ID
     */
    public int getContinent(){
        return continent;
    }


    /**
     * Sets the border rectangle for the country.
     *
     * @param x the x-coordinate of the top-left corner
     * @param y the y-coordinate of the top-left corner
     * @param width the width of the border
     * @param height the height of the border
     */
    public void setBord(int x, int y, int width, int height){
        borders = new Rectangle(x, y, width, height);
    }


    /**
     * Retrieves the border rectangle of the country.
     *
     * @return a Rectangle defining the country's border
     */
    public Rectangle giveBorder(){
        return borders;
    }



    /**
     * Retrieves the ID of the player currently possessing the country.
     *
     * @return an integer representing the player's ID
     */
    public int getPossession(){
        return possession;
    }



    /**
     * Retrieves the number of armies stationed in the country.
     *
     * @return an integer representing the number of armies
     */
    public int getArmies(){
        return armies;
    }



    /**
     * Simulates an attack on another country.
     *
     * @param a the country to attack
     */
    public void attack(Country a){
        int temp = diceRoll.nextInt(6);
        int temp2 = a.defend();
        if (temp < temp2){
            lose(1);
            if(getArmies() <= 0){
                defeated();
            }
        }
        else if (temp > temp2){
            a.lose(1);
            if(a.getArmies() <= 0){
                a.defeated();
            }
        }
    }



    /**
     * Simulates a defensive roll for the country.
     *
     * @return an integer representing the defense roll
     */
    public int defend(){
        int temp = diceRoll.nextInt(6);
        return temp;
    }




    /**
     * Retrieves the name of the country.
     *
     * @return the name of the country
     */
    public String getCountry() {
        return country;
    }


    /**
     * Sets the name of the country.
     *
     * @param country the name to assign to this country
     */
    public void setCountry(String country) {
        this.country = country;
    }



    /**
     * Retrieves the neighbors of this country.
     *
     * @return a set of neighboring countries
     */
    public Set<Country> getNeighbors() {
        return neighbors;
    }



    /**
     * Reduces the number of armies stationed in the country.
     *
     * @param a the number of armies to lose
     */
    public void lose(int a){
        armies -= a;
    }



    /**
     * Increases the number of armies stationed in the country.
     *
     * @param a the number of armies to gain
     */
    public void gain(int a){
        armies += a;
    }



    /**
     * Marks the country as conquered by a given player.
     *
     * @param a the ID of the player who conquered the country
     */
    public void conqueredBy(int a){
        possession = a;
        armies = 1;
    }



    /**
     * Marks the country as defeated and clears its armies.
     */
    public void defeated(){
        empty = true;
    }




    /**
     * Checks if the country is empty and updates its state.
     *
     * @return true if the country was previously empty, false otherwise
     */
    public boolean isEmpty(){
        boolean temp = empty;
        empty = false;
        return temp;
    }


    /**
     * Retrieves all neighboring countries.
     *
     * @return a set of neighboring countries
     */
    public Set<Country> getCountries() {
        // Returns the set of neighboring countries associated with this country
        return neighbors;
    }


    /**
     * Checks if the country is strategic based on its number of neighbors.
     *
     * @return true if the country has more than 2 neighbors, false otherwise
     */
    public boolean isStrategic() {
        // Define strategic criteria. For instance, a country with many neighbors can be considered strategic.
        return neighbors.size() > 2;  // Example criterion: having more than 2 neighbors
    }
}
