package org.example;
import org.example.*;
import java.awt.*;
import java.util.*;

public class Player{
    Color color;
    String name, colorname;

    int player, armies, income, NA, SA, EU, AF, AS, OC, IN, CA, CN, WI;

    ArrayList<Country> countries;
    ArrayList<Card> cards;
    ArrayList<Card> cardsCashed;

    public Player(String a, int b, Color c){ //makes a player. variables are self-explanatory
        name = a;
        player = b;
        color = c;


        countries = new ArrayList<>();
        cards = new ArrayList<>();
        cardsCashed = new ArrayList<>();
        }


        private String getColorNameFromColor(Color c) {
            if (c.equals(Color.RED)) return "red";
            if (c.equals(Color.BLUE)) return "blue";
            if (c.equals(Color.GREEN)) return "green";
            if (c.equals(Color.YELLOW)) return "yellow";
            if (c.equals(Color.BLACK)) return "black";
            if (c.equals(Color.GRAY)) return "gray";
            return "unknown";
        }



    public void start(){ //starts up all of the player's countries
        for (Country country : countries) {
            country.start(player);
        }
    }

    public int getPlayer(){
        return player;
    }

    public Color getColor(){
        return color;
    }
    
    public String getColorName(){
        return colorname;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Country> getCountries(){
        return countries;
    }

    public void conquered(Country a){
        countries.add(a);
    }

    public void lost(Country a){
        countries.remove(a);
    }

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

        private void resetContinentCounters () {
            NA = SA = EU = AF = AS = OC = 0;
        }

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

    public int getCardsN(){
        return cards.size();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void gotCard(Card a){
        cards.add(a);
        a.heldBy(player);
    }

    public void gotCards(Player a){
        cards.addAll(a.lostCards());
    }

    public ArrayList<Card> lostCards(){
        ArrayList<Card> temp = new ArrayList<>(cards);
        cards.clear();
        return temp;
    }

    public void cashedCard(Card a){
        cards.remove(a);
        a.heldBy(-1);
        cardsCashed.add(a);
    }

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

    public ArrayList<Card> returnedCards(){
        ArrayList<Card> temp = new ArrayList<>(cardsCashed);
        cardsCashed.clear();
        return temp;
    }
}
