package org.example;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Country{ //everything is pretty clear
    int armies, possession, continent;
    boolean empty;

       String country;
    
    Set<Country> neighbors;
    Rectangle borders;
    Image selected;
    
    Random diceRoll;
    
    public Country(String a, int b){
        country = a;
        continent = b;
        diceRoll = new Random();
        neighbors = new HashSet<>();
        empty = true;
    }

    public void selectedImage(Image a){
        selected = a;
    }
    
    public void start(int a){
        possession = a;
        armies = 5;
        empty = false;
    }
    
    public void addNeighbor(Country a){
        neighbors.add(a);
    }
    
    public void addNeighbor(ArrayList<Country> a, String b){
        for (int c = 0; c < 42; c++){
            if (!neighbors.contains(a.get(c)) && b.equals(a.get(c).getName())){
                neighbors.add(a.get(c));
                break;
            }
        }
    }
    
    public void removeNeighbor(ArrayList<Country> a, String b){
        for (int c = 0; c < 42; c++){
            if (neighbors.contains(a.get(c)) && b.equals(a.get(c).getName())){
                neighbors.remove(a.get(c));
                break;
            }
        }
    }
    
    public boolean isNeighbor(Country a){
        for (Country neighbor : neighbors) {
            if (neighbor == a) {
                return true;
            }
        }
        return false;
    }


    
    public Image getSelected(){
        return selected;
    }
    
        public String getName(){
        return country;
    }
    
    public int getContinent(){
        return continent;
    }
    
    public void setBord(int x, int y, int width, int height){
        borders = new Rectangle(x, y, width, height);
    }
    
    public Rectangle giveBorder(){
        return borders;
    }
    
    public int getPossession(){
        return possession;
    }
    
    public int getArmies(){
        return armies;
    }
    
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
    
    public int defend(){
        int temp = diceRoll.nextInt(6);
        return temp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public Set<Country> getNeighbors() {
        return neighbors;
    }



    public void lose(int a){
        armies -= a;
    }
    
    public void gain(int a){
        armies += a;
    }
        
    public void conqueredBy(int a){
        possession = a;
    }
    
    public void defeated(){
        empty = true;
    }
    
    public boolean isEmpty(){
        boolean temp = empty;
        empty = false;
        return temp;
    }

    public Set<Country> getCountries() {
        // Returns the set of neighboring countries associated with this country
        return neighbors;
    }

    public boolean isStrategic() {
        // Define strategic criteria. For instance, a country with many neighbors can be considered strategic.
        return neighbors.size() > 2;  // Example criterion: having more than 2 neighbors
    }
}
