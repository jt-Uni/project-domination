package core;
import core.*;
import java.awt.*;
import java.util.*;






/**
 * The Player class represents a player in the World Conquest game.
 * It manages a player's properties, including countries, cards, and game mechanics.
 */
public class Player{
    Color color;
    String name, colorname;

    int player, armies, income, NA, SA, EU, AF, AS, OC, IN, CA, CN, WI;

    ArrayList<Country> countries;
    ArrayList<Card> cards;
    ArrayList<Card> cardsCashed;





    /**
     * Constructs a Player object with a name, ID, and color.
     *
     * @param a the name of the player
     * @param b the ID of the player
     * @param c the color representing the player
     */
    public Player(String a, int b, Color c){ //makes a player. variables are self-explanatory
        name = a;
        player = b;
        color = c;


        countries = new ArrayList<>();
        cards = new ArrayList<>();
        cardsCashed = new ArrayList<>();
        }


    /**
     * Retrieves the name of the color assigned to a player.
     *
     * @param c the color to convert
     * @return a string representing the color's name
     */
    private String getColorNameFromColor(Color c) {
            if (c.equals(Color.RED)) return "red";
            if (c.equals(Color.BLUE)) return "blue";
            if (c.equals(Color.GREEN)) return "green";
            if (c.equals(Color.YELLOW)) return "yellow";
            if (c.equals(Color.BLACK)) return "black";
            if (c.equals(Color.GRAY)) return "gray";
            return "unknown";
        }



    /**
     * Initializes the player's owned countries.
     */
    public void start(){ //starts up all of the player's countries
        for (Country country : countries) {
            country.start(player);
        }
    }


    /**
     * Retrieves the player's ID.
     *
     * @return the player's ID
     */
    public int getPlayer(){
        return player;
    }


    /**
     * Retrieves the color assigned to the player.
     *
     * @return the player's color
     */
    public Color getColor(){
        return color;
    }


    /**
     * Retrieves the player's color name.
     *
     * @return the name of the player's color
     */
    public String getColorName(){
        return colorname;
    }


    /**
     * Retrieves the player's name.
     *
     * @return the player's name
     */
    public String getName(){
        return name;
    }



    /**
     * Retrieves the countries owned by the player.
     *
     * @return a list of countries owned by the player
     */
    public ArrayList<Country> getCountries(){
        return countries;
    }


    /**
     * Adds a conquered country to the player's ownership.
     *
     * @param a the country conquered
     */
    public void conquered(Country a){
        countries.add(a);
    }



    /**
     * Removes a lost country from the player's ownership.
     *
     * @param a the country lost
     */
    public void lost(Country a){
        countries.remove(a);
    }



    /**
     * Calculates and returns the player's income based on owned countries and continent bonuses.
     *
     * @return the player's income
     */
    public int getIncome() { //calculates income

        income = countries.size() / 3;
        if (income < 3) {
            income = 3;
        }

        for (Country c : countries) {
            switch (c.getContinent()) {
                case 0:
                    NA++;
                    break;
                case 1:
                    SA++;
                    break;
                case 2:
                    EU++;
                    break;
                case 3:
                    AF++;
                    break;
                case 4:
                    AS++;
                    break;
                case 5:
                    OC++;
                    break;
            }
        }

        // Adds continent bonuses
        income += getContinentBonuses();

        resetContinentCounters(); // Reset after use
        return income;

    }



    /**
     * Resets continent-specific counters.
     */
    private void resetContinentCounters () {
            NA = SA = EU = AF = AS = OC = 0;
        }



    /**
     * Calculates bonuses based on the player's control over continents.
     *
     * @return the total bonus income
     */
   private int getContinentBonuses () {
            int bonus = 0;

            if (NA == 9) bonus += 5;
            if (SA == 4) bonus += 2;
            if (EU == 7) bonus += 5;
            if (AF == 6) bonus += 3;
            if (AS == 12) bonus += 7;
            if (OC == 4) bonus += 2;

            return bonus;
        }



    /**
     * Retrieves the number of cards owned by the player.
     *
     * @return the number of cards
     */
    public int getCardsN(){
        return cards.size();
    }




    /**
     * Retrieves the cards owned by the player.
     *
     * @return a list of cards
     */
    public ArrayList<Card> getCards(){
        return cards;
    }



    /**
     * Adds a new card to the player's hand.
     *
     * @param a the card received
     */
    public void gotCard(Card a){
        cards.add(a);
        a.heldBy(player);
    }



    /**
     * Adds all cards from another player to this player's hand.
     *
     * @param a the player from whom the cards are taken
     */
    public void gotCards(Player a){
        cards.addAll(a.lostCards());
    }




    /**
     * Retrieves all cards lost by the player.
     *
     * @return a list of lost cards
     */
    public ArrayList<Card> lostCards(){
        ArrayList<Card> temp = new ArrayList<>(cards);
        cards.clear();
        return temp;
    }


    /**
     * Adds a cashed card to the player's cashed card list.
     *
     * @param a the card cashed
     */
    public void cashedCard(Card a){
        cards.remove(a);
        a.heldBy(-1);
        cardsCashed.add(a);
    }


    /**
     * Checks if the player can cash in cards.
     *
     * @return true if the player can cash in cards, false otherwise
     */
    public boolean ableToCash(){ //whether or not player can cash
        IN = 0;
        CA = 0;
        CN = 0;
        WI = 0;
        for (Card ca : cards){
            switch (ca.getUnit()) {
                case 0 -> IN++;
                case 1 -> CA++;
                case 2 -> CN++;
                case 3 -> WI++;
            }
        }
        return (IN >= 3) || (CA >= 3) || (CN >= 3) || (IN >= 1 && CA >= 1 && CN >= 1) || (WI >= 1 && IN + CA + CN + WI - 1 >= 2);
    }



    /**
     * Checks if a specific card set can be cashed.
     *
     * @param a the first card
     * @param b the second card
     * @param c the third card
     * @return true if the set can be cashed, false otherwise
     */
    public boolean cashable(Card a, Card b, Card c){ //whether set is cashable
        IN = 0;
        CA = 0;
        CN = 0;
        WI = 0;
        switch (a.getUnit()) {
            case 0 -> IN++;
            case 1 -> CA++;
            case 2 -> CN++;
            case 3 -> WI++;
        }
        switch (b.getUnit()) {
            case 0 -> IN++;
            case 1 -> CA++;
            case 2 -> CN++;
            case 3 -> WI++;
        }
        switch (c.getUnit()){
            case 0:     IN++;   break;
            case 1:     CA++;   break;
            case 2:     CN++;   break;
            case 3:     WI++;   break;
        }
        if ((IN >= 3) || (CA >= 3) ||(CN >= 3) || (IN >= 1 && CA >= 1 && CN >= 1) || (WI >= 1 && IN + CA + CN + WI - 1 >= 2)){
            return true;
        }
        else{
            return false;
        }
    }




    /**
     * Cashes in a card set and provides rewards.
     *
     * @param a the first card
     * @param b the second card
     * @param c the third card
     * @param d the reward list
     * @param e the index of the current reward
     * @return the reward amount
     */
    public int cash(Card a, Card b, Card c, ArrayList<Integer> d, int e){ //cashing the set
        for (Country co : countries){
            if(co.getName().equals(a.getCountry())){
                co.gain(2);
            }
        }
        for (Country co : countries){
            if(co.getName().equals(b.getCountry())){
                co.gain(2);
            }
        }
        for (Country co : countries){
            if(co.getName().equals(c.getCountry())){
                co.gain(2);
            }
        }
        cashedCard(a);
        cashedCard(b);
        cashedCard(c);
        return d.get(e);
    }



    /**
     * Returns all cards that have been cashed in by the player.
     *
     * @return a list of cashed cards
     */
    public ArrayList<Card> returnedCards(){
        ArrayList<Card> temp = new ArrayList<>(cardsCashed);
        cardsCashed.clear();
        return temp;
    }
}
