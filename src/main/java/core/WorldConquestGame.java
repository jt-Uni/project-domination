package core;
import javax.swing.*;
import javax.swing.Timer;

import aiEngine.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;






public class WorldConquestGame extends JFrame implements MouseListener, MouseMotionListener{
    boolean isActive, clicked, selected, attack, view, getCard, conquered, fortify, fortified, place, moving, a, v, f, end, done, cash, quit;
    BufferedImage screen;
    Image map, actions;
    String x, y;
    public ArrayList<Country> Countries;
    public ArrayList<Player> Players;
    ArrayList<Card> Cards;
    ArrayList<Integer> Rewards;
    int select, active, turn, income, fortify1, fortify2, cashIn, cash1, cash2, cash3, reward;
    Dimension dim;
    Rectangle Attack, View, Fortify, End, Done, CashIn, Quit;
    Random r;
    StartScreen Start;
    JFrame frame;
    private static final String WINDOW_TITLE = "World Conquest";

    private Random random; // For generating random numbers





    public WorldConquestGame() {
        super(WINDOW_TITLE);
        initializeGame();
    }



    private void initializeGame() {
        random = new Random();
        Start = new StartScreen();
        waitForStartScreen(Start);
        setupUI();
        initializeGameState();
        setupCountries();
        setupPlayers();
        setupCards();
        setupRewards();
        finalisation();
        showInitialDialog();
    }



    private void waitForStartScreen(StartScreen start) {
        while (!start.started()) {
            if (!start.isVisible()) {
                System.exit(0);
            }
            Thread.yield();
        }
    }




    private void setupUI() {
        setLayout(new BorderLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }



    private void initializeGameState() {
        isActive = false;
        clicked = false;
        attack = false;
        view = false;
        fortify = false;
        fortified = false;
        conquered = false;
        getCard = false;
        moving = false;
        place = true;
        turn = 0;
        reward = 0;

    }




    private void setupCountries() {

        Attack = new Rectangle((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*360), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        View = new Rectangle((int)((dim.getWidth()/1920.0)*1765), (int)((dim.getHeight()/1080.0)*360), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        Fortify = new Rectangle((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*460), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        End = new Rectangle((int)((dim.getWidth()/1920.0)*1765), (int)((dim.getHeight()/1080.0)*460), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        Quit = new Rectangle((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*560), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));

        CashIn = new Rectangle((int)((dim.getWidth()/1920.0)*760), (int)((dim.getHeight()/1080.0)*900), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        Done = new Rectangle((int)((dim.getWidth()/1920.0)*1060), (int)((dim.getHeight()/1080.0)*900), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));




        Countries = new ArrayList<>();

        //North america = 10
        Countries.add(new Country("Alaska", 0));
        Countries.get(0).setBord(145,255,75,75);
        Countries.add(new Country("Alberta", 0));
        Countries.get(1).setBord(301,325,62,36);
        Countries.add(new Country("CentralAmerica", 0));
        Countries.get(2).setBord(269,518,71,69);
        Countries.add(new Country("EasternUnitedStates", 0));
        Countries.get(3).setBord(300,416,115,109);
        Countries.add(new Country("Greenland", 0));
        Countries.get(4).setBord(449,169,150,100);
        Countries.add(new Country("NorthwestTerritory", 0));
        Countries.get(5).setBord(244,225,124,88);
        Countries.add(new Country("Ontario", 0));
        Countries.get(6).setBord(342,316,48,45);
        Countries.add(new Country("NorthernUnitedStates", 0));
        Countries.get(7).setBord(289,375,158,33);
        Countries.add(new Country("WesternUnitedStates", 0));
        Countries.get(8).setBord(265,414,74,78);
        Countries.add(new Country("EasternCanada", 0));
        Countries.get(9).setBord(389,355,158,33);

        // South America = 5
        Countries.add(new Country("Argentina", 1));
        Countries.get(10).setBord(313,815,151,119);
        Countries.add(new Country("Brazil", 1));
        Countries.get(11).setBord(424,669,171,92);
        Countries.add(new Country("Peru", 1));
        Countries.get(12).setBord(274,690,119,106);
        Countries.add(new Country("Bolivia", 1));
        Countries.get(13).setBord(300,650,132,28);
        Countries.add(new Country("Venezuela", 1));
        Countries.get(14).setBord(244,620,115,17);

        // Europe = 9
        Countries.add(new Country("Sweden", 2));
        Countries.get(15).setBord(650,231,114,50);
        Countries.add(new Country("Iceland", 2));
        Countries.get(16).setBord(595,200,58,47);
        Countries.add(new Country("Finland", 2));
        Countries.get(17).setBord(792,218,77,50);
        Countries.add(new Country("Poland", 2));
        Countries.get(18).setBord(833,270,90,22);
        Countries.add(new Country("SouthernEurope", 2));
        Countries.get(19).setBord(750,384,87,31);
        Countries.add(new Country("Ukraine", 2));
        Countries.get(20).setBord(880,324,98,54);
        Countries.add(new Country("Spain", 2));
        Countries.get(21).setBord(700,425,98,55);
        Countries.add(new Country("Text", 2));
        Countries.get(22).setBord(935,197,70,48);
        Countries.add(new Country("France", 2));
        Countries.get(23).setBord(722,287,69,47);

        // Africa = 7
        Countries.add(new Country("Algeria", 3));
        Countries.get(24).setBord(730,547,114,117);
        Countries.add(new Country("Chad", 3));
        Countries.get(25).setBord(850,634,82,52);
        Countries.add(new Country("Egypt", 3));
        Countries.get(26).setBord(850,600,86,23);
        Countries.add(new Country("Madagascar", 3));
        Countries.get(27).setBord(1000,880,67,42);
        Countries.add(new Country("Sudan", 3));
        Countries.get(28).setBord(964,602,97,64);
        Countries.add(new Country("SouthAfrica", 3));
        Countries.get(29).setBord(853,800,147,60);
        Countries.add(new Country("CentralAfrica", 3));
        Countries.get(30).setBord(853,700,147,60);

        // Asia = 12
        Countries.add(new Country("Afghanistan", 4));
        Countries.get(31).setBord(999,380,104,24);
        Countries.add(new Country("China", 4));
        Countries.get(32).setBord(1200,335,84,39);
        Countries.add(new Country("India", 4));
        Countries.get(33).setBord(1120,416,56,56);
        Countries.add(new Country("pakistan", 4));
        Countries.get(34).setBord(1124,345,58,59);
        Countries.add(new Country("Japan", 4));
        Countries.get(35).setBord(1368,305,68,97);
        Countries.add(new Country("Kamchatka", 4));
        Countries.get(36).setBord(1281,163,94,68);
        Countries.add(new Country("MiddleEast", 4));
        Countries.get(37).setBord(942,419,101,79);
        Countries.add(new Country("Mongolia", 4));
        Countries.get(38).setBord(1147,236,133,60);
        Countries.add(new Country("SouthEastAsia", 4));
        Countries.get(39).setBord(1300,380,63,53);
        Countries.add(new Country("Siberia", 4));
        Countries.get(40).setBord(1100,214,83,65);
        Countries.add(new Country("Ural", 4));
        Countries.get(41).setBord(1015,245,83,80);
        Countries.add(new Country("Yakutsk", 4));
        Countries.get(42).setBord(1200,142,117,79);

        // Australia = 5
        Countries.add(new Country("EasternAustralia", 5));
        Countries.get(43).setBord(1340,673,85,114);
        Countries.add(new Country("Indonesia", 5));
        Countries.get(44).setBord(1155,593,127,66);
        Countries.add(new Country("NewGuinea", 5));
        Countries.get(45).setBord(1262,531,121,111);
        Countries.add(new Country("WesternAustralia", 5));
        Countries.get(46).setBord(1200,718,92,105);
        Countries.add(new Country("CentralAustralia", 5));
        Countries.get(47).setBord(1254,711,72,81);

        for (int c = 0; c < 48; c++){ //resizing, relocating and giving a picture and a border to every country.
            Countries.get(c).selectedImage(new ImageIcon("./assets/countries/" + Countries.get(c).getName() + ".jpg").getImage());
            Countries.get(c).selectedImage(resize(Countries.get(c).getSelected(), (int)((dim.getWidth()/1920.0)*1576), (int)((dim.getHeight()/1080.0)*1050)));
            Countries.get(c).setBord((int)((dim.getWidth()/1920.0)*Countries.get(c).giveBorder().x) + (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*Countries.get(c).giveBorder().y) + (int)((dim.getHeight()/1080.0)*15), (int)((dim.getWidth()/1920.0)*Countries.get(c).giveBorder().width), (int)((dim.getHeight()/1080.0)*Countries.get(c).giveBorder().height));
            Rectangle temp = new Rectangle (Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - (int)((dim.getWidth()/1920.0)*150), Countries.get(c).giveBorder().y + (Countries.get(c).giveBorder().height/2) - (int)((dim.getHeight()/1080.0)*150), (int)((dim.getWidth()/1920.0)*300), (int)((dim.getHeight()/1080.0)*300));
        }

        for (int c = 0; c < 48; c++){ //attempting to get all the neighbors for a country.
            Rectangle temp = new Rectangle (Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - (int)((dim.getWidth()/1920.0)*150), Countries.get(c).giveBorder().y + (Countries.get(c).giveBorder().height/2) - (int)((dim.getHeight()/1080.0)*150), (int)((dim.getWidth()/1920.0)*300), (int)((dim.getHeight()/1080.0)*300));
            for (int c2 = 0; c2 < 48; (c2)++){
                Point p1 = Countries.get(c2).giveBorder().getLocation();
                Point p2 = Countries.get(c2).giveBorder().getLocation();
                p2.translate(Countries.get(c2).giveBorder().width, 0);
                Point p3 = Countries.get(c2).giveBorder().getLocation();
                p3.translate(0, Countries.get(c2).giveBorder().height);
                Point p4 = Countries.get(c2).giveBorder().getLocation();
                p4.translate(Countries.get(c2).giveBorder().width, Countries.get(c2).giveBorder().height);
                if (temp.contains(p1) || temp.contains(p2) || temp.contains(p3) ||temp.contains(p4)){
                    if (c != c2){
                        Countries.get(c).addNeighbor(Countries.get(c2));
                    }
                }
            }
        }

        String[] countryNames = {"Alaska", "Alberta", "Central America", "Eastern United States",
                         "Greenland", "Northwest Territory", "Ontario", "Northern United States",
                         "Western United States", "Eastern Canada", "Argentina", "Brazil",
                         "Peru", "Bolivia", "Venezuela", "Sweden", "Iceland", "Finland",
                         "Poland", "Southern Europe", "Ukraine", "Spain", "Text", "France",
                         "Algeria", "Chad", "Egypt", "Madagascar", "Sudan", "South Africa",
                         "Central Africa", "Afghanistan", "China", "India", "Pakistan",
                         "Japan", "Kamchatka", "Middle East", "Mongolia", "South East Asia",
                         "Siberia", "Ural", "Yakutsk", "Eastern Australia", "Indonesia",
                         "New Guinea", "Western Australia", "Central Australia"};

        String[][] neighbors = {
            {"Alberta", "Northwest Territory", "Northern United States"}, // Alaska
            {"Alaska", "Northwest Territory", "Ontario", "Northern United States"}, // Alberta
            {"Venezuela", "Western United States", "Eastern United States"}, // Central America
            {"Western United States", "Central America", "Northern United States", "Eastern United States"}, // Eastern US
            {"Iceland", "Eastern Canada", "Ontario", "Northwest Territory"}, // Greenland
            {"Alaska", "Alberta", "Ontario", "Greenland"}, // Northwest Territory
            {"Alberta", "Eastern Canada", "Northern United States", "Northwest Territory", "Greenland"}, // Ontario
            {"Ontario", "Alberta", "Eastern Canada", "Western United States", "Eastern United States", "Alaska"}, // Northern US
            {"Northern United States", "Eastern United States", "Central America"}, // Western US
            {"Greenland", "Ontario", "Northern United States", "Eastern United States"}, // Eastern Canada
            {"Peru", "Brazil"}, // Argentina
            {"Bolivia", "Peru", "Argentina"}, // Brazil
            {"Venezuela", "Bolivia", "Brazil", "Argentina"}, // Peru
            {"Peru", "Brazil", "Venezuela"}, // Bolivia
            {"Central America", "Bolivia", "Peru"}, // Venezuela
            {"Finland", "France", "Iceland"}, // Sweden
            {"Greenland", "Sweden"}, // Iceland
            {"Sweden", "France", "Poland", "Text"}, // Finland
            {"Finland", "France", "Ukraine", "Text"}, // Poland
            {"France", "Ukraine", "Middle East", "Spain"}, // Southern Europe
            {"Poland", "France", "Middle East", "Southern Europe", "Text", "Ural", "Afghanistan"}, // Ukraine
            {"Southern Europe", "Algeria"}, // Spain
            {"Poland", "Ukraine", "Ural", "Finland"}, // Text
            {"Southern Europe", "Ukraine", "Finland", "Sweden", "Finland"}, // France
            {"Spain", "Egypt", "Chad"}, // Algeria
            {"Algeria", "Sudan", "Central Africa", "Egypt"}, // Chad
            {"Algeria", "Sudan", "Middle East", "Chad"}, // Egypt
            {"Middle East"}, // Madagascar
            {"Chad", "Egypt", "Central Africa"}, // Sudan
            {"Central Africa", "Madagascar"}, // South Africa
            {"Chad", "Sudan", "South Africa"}, // Central Africa
            {"Ural", "Ukraine", "Middle East", "Pakistan", "India"}, // Afghanistan
            {"Mongolia", "Pakistan", "India", "South East Asia"}, // China
            {"China", "Pakistan", "Afghanistan", "Indonesia"}, // India
            {"Afghanistan", "India", "China", "Siberia"}, // Pakistan
            {"Mongolia", "South East Asia"}, // Japan
            {"Mongolia", "Japan", "Yakutsk"}, // Kamchatka
            {"Ukraine", "Afghanistan", "Southern Europe"}, // Middle East
            {"China", "Japan", "Kamchatka", "Siberia", "Yakutsk", "South East Asia"}, // Mongolia
            {"China", "Japan", "Mongolia"}, // South East Asia
            {"Ural", "Pakistan", "Yakutsk", "Mongolia"}, // Siberia
            {"Ukraine", "Afghanistan", "Siberia", "Text"}, // Ural
            {"Kamchatka", "Mongolia", "Siberia"}, // Yakutsk
            {"New Guinea", "Central Australia"}, // Eastern Australia
            {"New Guinea", "Western Australia", "India", "Central Australia"}, // Indonesia
            {"Eastern Australia", "Central Australia", "Indonesia", "South East Asia"}, // New Guinea
            {"Central Australia", "Indonesia"}, // Western Australia
            {"Eastern Australia", "New Guinea", "Indonesia", "Western Australia"} // Central Australia
        };



        for (int i = 0; i < countryNames.length; i++) {
            for (String neighbor : neighbors[i]) {
                Countries.get(i).addNeighbor(Countries, neighbor);
            }
        }
    }






    private void finalisation() {
        addMouseListener(this);
        addMouseMotionListener(this);

        income = Players.getFirst().getIncome();
        fortify1 = -1;
        fortify2 = -1;

        map = new ImageIcon("./assets/worldConquest.png").getImage();
        map = resize(map, (int)((dim.getWidth()/1920)*1576), (int)((dim.getHeight()/1080)*1050.0));
        actions = new ImageIcon("./assets/ActionPaneWithInfo.jpg").getImage();
        actions = resize(actions, (int)((dim.getWidth()/1920)*290), (int)((dim.getHeight()/1080)*1050.0));
        screen = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

        frame = new JFrame();

        buffer();
        setVisible(true);
    }





    private void setupPlayers() {
        Players = new ArrayList<>(Start.getPlayers());
        Cards = new ArrayList<>();
        r = new Random();

        ArrayList<Country> temp2 = new ArrayList<>(Countries);


        for (int c = 0; c < Players.size(); c++) { // distributing countries randomly
            for (int c2 = 48 / Players.size(); c2 > 0; c2--) {
                int temp = r.nextInt(temp2.size());
                Players.get(c).conquered(temp2.get(temp));
                temp2.remove(temp);
            }
        }

        while (!temp2.isEmpty()) {
            Players.get(turn).conquered(temp2.getFirst());
            temp2.removeFirst();
            turn++;
            if (turn >= Players.size()) turn = 0;
        }

        turn = 0;  // Reset turn to start the game from the beginning

        for (Player player : Players) {
            player.start();
        }
    }




    


    private void setupCards() {
        int unit = 0;
        for (int c = 0; c < 48; c++){ //making cards
            Cards.add(new Card(Countries.get(c).getName(), unit, -1));
            unit ++;
            if(unit == 3){
                unit = 0;
            }
        }
        Cards.add(new Card("Wild card", 3, -1));
        Cards.add(new Card("Wild card", 3, -1));
    }





    private void setupRewards() {
        Rewards = new ArrayList<>(); //setting rewards
        for (int c = 4; c < 13; c += 2){
            Rewards.add(Integer.parseInt(String.valueOf(c)));
        }
        for (int c = 15; c < 31; c += 3){
            Rewards.add(Integer.parseInt(String.valueOf(c)));
        }
        for (int c = 35; c < 61; c += 5){
            Rewards.add(Integer.parseInt(String.valueOf(c)));
        }
    }






    private void showInitialDialog() {
        Random r = new Random();

        // Roll dice for the first two players
        int diceRoll1 = r.nextInt(6) + 1;
        int diceRoll2 = r.nextInt(6) + 1;

        String message = String.format(
                "%s rolls a %d, and %s rolls a %d.\n",
                Players.get(0).getName(),
                diceRoll1,
                Players.get(1).getName(),
                diceRoll2
        );

        // Determine the winner
        if (diceRoll1 > diceRoll2) {
            turn = 0;  // Player 1 starts
            message += Players.get(turn).getName() + " wins the dice roll and will go first.";
        } else if (diceRoll1 < diceRoll2) {
            turn = 1;  // Player 2 starts
            message += Players.get(turn).getName() + " wins the dice roll and will go first.";
        } else {
            message += "It's a tie. Rolling again...";
            showInitialDialog();  // Recursively re-roll in case of a tie
            return;// continue the game
        }

        JOptionPane.showMessageDialog(frame, message, "Dice Roll", JOptionPane.INFORMATION_MESSAGE);

        // Additional logic to handle AI victory specifically:
        if (Players.get(turn) instanceof AIPlayer) {
            JOptionPane.showMessageDialog(frame,
                    "AI won the roll. It's the AI's turn, but it will wait for you to make a move.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Return control to the player without invoking aiTurn.
            return;
        }

        // Proceed normally for a human player's turn.
        JOptionPane.showMessageDialog(frame,
                "It is now " + Players.get(turn).getName() + "'s turn.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }






    public void mousePressed(MouseEvent e) {


        if (Players.get(turn).getCardsN() > 5) {
            view = true;
        }

        if (isActive && !view) {
            handleAttackOrFortify();

            if (selected && place) {
                placeArmies();
            } else if (Countries.get(active).getPossession() == turn) {
                selected = true;
                select = active;
            }
        } else {
            selected = false;
        }

        if (!place) {
            handleCustomButtons();
        }

        if (quit) {
            System.exit(0);
        }

        if (v) {
            viewCards();
        }

        if (isActive && view) {
            selectCards();
        }

        if (cash && place) {
            cashInCards();
        }

        if (done) {
            stopViewingCards();
        }

        buffer();
    }


    private void handleAttackOrFortify() {
        if (selected && select != active && Countries.get(select).isNeighbor(Countries.get(active))) {
            handleFortify();
            handleAttack();
        }
    }




    private void handleFortify() {
        if (conquered) {
            handleTroopMovement();
        } else if (fortify && Countries.get(active).getPossession() == turn) {
            fortifyPositions();
        }
    }




    private void handleTroopMovement() {
        if ((fortify1 == select && fortify2 == active) || (fortify1 == active && fortify2 == select)) {
            if (Countries.get(select).getArmies() > 1) {
                Countries.get(select).lose(1);
                Countries.get(active).gain(1);
            }
        } else {
            moving = false;
            fortify1 = -1;
            fortify2 = -1;
        }
    }




    private void fortifyPositions() {
        if (!fortified && Countries.get(select).getArmies() > 1) {
            fortified = true;
            fortify1 = select;
            fortify2 = active;
        }
        if ((fortify1 == select && fortify2 == active) || (fortify1 == active && fortify2 == select)) {
            if (Countries.get(select).getArmies() > 1 && fortified) {
                Countries.get(select).lose(1);
                Countries.get(active).gain(1);
            }
        }
    }

    private void handleAttack() {
        if (attack && Countries.get(active).getPossession() != turn && Countries.get(select).getArmies() > 1) {
            // Step 1: Roll dice for attacker and defender.
            int attackerRoll = r.nextInt(6) + 1;
            int defenderRoll = r.nextInt(6) + 1;

            String resultMessage = String.format(
                    "Attacker rolled: %d\nDefender rolled: %d\n",
                    attackerRoll, defenderRoll
            );

            // Step 2: Determine the winner and handle outcomes.
            if (attackerRoll > defenderRoll) {
                resultMessage += "Attacker wins!";
                Countries.get(select).attack(Countries.get(active));
                conquered = true;

                // Further conquest handling.
                if (Countries.get(active).isEmpty()) {
                    processConquest();
                }
            } else {
                resultMessage += "Defender wins! Turn ends.";
                JOptionPane.showMessageDialog(frame, resultMessage, "Attack Result", JOptionPane.INFORMATION_MESSAGE);
                endTurn(); // End turn immediately if the defender wins.
                return;
            }

            // Show the final result dialog.
            JOptionPane.showMessageDialog(frame, resultMessage, "Attack Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    // Revised processConquest() function:
    private void processConquest() {
        Player conqueringPlayer = Players.get(Countries.get(select).getPossession());
        Player conqueredPlayer = Players.get(Countries.get(active).getPossession());

        // Update territory ownership:
        Countries.get(active).conqueredBy(conqueringPlayer.getPlayer());

        // Notify about the conquest:
        JOptionPane.showMessageDialog(frame,
                conqueringPlayer.getName() + " has conquered " + Countries.get(active).getName() + "!",
                "Conquest Notification",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Transfer the conquered country to the victor:
        conqueringPlayer.conquered(Countries.get(active));
        conqueredPlayer.lost(Countries.get(active));

        // Check for player defeat and overall victory:
        checkPlayerDefeat(conqueredPlayer);
        checkGameVictory();
        // Handle card giving after conquest
        handleCard();
    }





    private void checkPlayerDefeat(Player conqueredPlayer) {
        if (conqueredPlayer.getCountries().isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    Players.get(turn).getName() + " has defeated " + conqueredPlayer.getName() + "!",
                    "Player Defeated",
                    JOptionPane.INFORMATION_MESSAGE
            );
            Players.remove(conqueredPlayer); // Remove the player from the game
        }
    }





    private void handleCard() {
        getCard = true;
        int temp = r.nextInt(Cards.size());
        Players.get(Countries.get(select).getPossession()).gotCard(Cards.get(temp));
        Cards.remove(temp);
    }


    private void checkPlayerDefeat() {
        Player defeatedPlayer = Players.get(Countries.get(active).getPossession());
        if (defeatedPlayer.getCountries().isEmpty()) {
            Player victor = Players.get(Countries.get(select).getPossession());

            victor.gotCards(defeatedPlayer); // Transfer cards from defeated player to victor
            JOptionPane.showMessageDialog(frame,
                    victor.getName() + " has defeated " + defeatedPlayer.getName() + "!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
            waitForDialog();

            checkGameVictory();
        }
    }





    private void checkGameVictory() {
        if (Objects.equals(Players.get(turn).getCountries(), 48)) {
            JOptionPane.showMessageDialog(frame,
                    "Congratulations! You have conquered the world!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
            waitForDialog();
            System.exit(0);
        }

        if (Players.get(turn).getCardsN() > 5) {
            promptForCashIn();
        }
    }







    private void promptForCashIn() {
        JOptionPane.showMessageDialog(frame,
                "You have more than 5 cards. Please cash in.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
        waitForDialog();
        view = true;
        isActive = false;
        cash1 = -1;
        cash2 = -1;
        cash3 = -1;
        cashIn = 1;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }




    private void finalizeConquest() {
        Players.get(Countries.get(select).getPossession()).conquered(Countries.get(active));
        Players.get(Countries.get(active).getPossession()).lost(Countries.get(active));
        Countries.get(active).conqueredBy(Countries.get(select).getPossession());

        conquered = true;
        moving = true;
        Countries.get(select).lose(1);
        Countries.get(active).gain(1);
        fortify1 = select;
        fortify2 = active;
    }




    private void placeArmies() {
        if (income <= 0) {
            place = false;
            return;
        }

        Countries.get(select).gain(1);
        income--;

        if (income == 0) {
            place = false;
        }

        buffer();
    }



    private void handleCustomButtons() {
        if ((a) && (!fortified)) {
            attack = true;
            fortify = false;
        }
        if ((f) && (!fortified)) {
            attack = false;
            fortify = true;

            if (!fortified) {
                fortify1 = -1;
                fortify2 = -1;
            }
        }
        if (end) {
            endTurn();
        }
    }




    public void endTurn() {
        turn++;
        if (turn >= Players.size()) turn = 0;

        income = Players.get(turn).getIncome();

        resetTurnState(); // Reset turn-specific states like attack, fortify, etc.

        showTurnDialog(); // Notify whose turn it is

        if (Players.get(turn) instanceof AIPlayer) {
            aiTurn((AIPlayer) Players.get(turn)); // Handle AI's turn
        } else {
            buffer(); // Allow user interaction, refreshing the game view
        }
    }




    private void aiTurn(AIPlayer aiPlayer) {
        aiPlayer.takeTurn(this); // Execute AI's turn based on its strategy

        // Revised handling for attacks:
        Set<Country> ownCountries = new HashSet<>(aiPlayer.getCountries());

        for (Country country : ownCountries) {
            for (Country neighbor : country.getNeighbors()) {
                if (neighbor.getPossession() != aiPlayer.getPlayer() && country.getArmies() > neighbor.getArmies()) {
                    // Roll dice to determine the outcome
                    int attackerRoll = rollDice();
                    int defenderRoll = rollDice();

                    String resultMessage = String.format(
                            "Attacker (AI) rolled: %d\nDefender rolled: %d\n",
                            attackerRoll, defenderRoll
                    );

                    if (attackerRoll > defenderRoll) {
                        // AI wins the attack
                        resultMessage += "AI wins the attack!";
                        country.attack(neighbor); // Attack the territory

                        // Check if the territory is conquered
                        if (neighbor.isEmpty()) {
                            neighbor.conqueredBy(aiPlayer.getPlayer());

                            JOptionPane.showMessageDialog(frame, resultMessage, "Attack Result", JOptionPane.INFORMATION_MESSAGE);
                            continue; // Skip further attacks on this territory
                        }
                    } else {
                        // Defender wins the attack
                        resultMessage += "Defender wins! AI turn ends.";
                        JOptionPane.showMessageDialog(frame, resultMessage, "Attack Result", JOptionPane.INFORMATION_MESSAGE);
                        return; // End AI turn immediately
                    }

                    JOptionPane.showMessageDialog(frame, resultMessage, "Attack Result", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        // Proceed with fortifying after attacking
        aiPlayer.fortifyCountries();
        endTurn(); // End AI turn
    }

    private int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1; // Return a number between 1 and 6
    }







    private void showTurnDialog() {


        buffer();
        String message = "It is now " + Players.get(turn).getName() + "'s turn.";
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);

        JDialog dialog = optionPane.createDialog(frame, "Information");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Timer timer = new Timer(3000, e -> dialog.dispose());  // Auto-close after 3 seconds
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);  // Display the dialog
    }











    private void resetTurnState() {
        attack = false;
        place = true;
        fortify = false;
        fortified = false;
        getCard = false;
        moving = false;
        fortify1 = -1;
        fortify2 = -1;
    }








    private void viewCards() {
        view = true;
        isActive = false;
        cash1 = -1;
        cash2 = -1;
        cash3 = -1;
        cashIn = 1;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }



    private void selectCards() {
        if (cashIn == 4) {
            cashIn = 1;
        }

        switch (cashIn) {
            case 1:
                if (active != cash2 && active != cash3) {
                    cash1 = active;
                    cashIn++;
                }
                break;
            case 2:
                if (active != cash1 && active != cash3) {
                    cash2 = active;
                    cashIn++;
                }
                break;
            case 3:
                if (active != cash1 && active != cash2) {
                    cash3 = active;
                    cashIn++;
                }
                break;
        }
    }

    private void cashInCards() {
        if (cash1 == -1 || cash2 == -1 || cash3 == -1) {
            return;
        }

        if (Players.get(turn).cashable(Cards.get(cash1), Cards.get(cash2), Cards.get(cash3))) {
            income = Players.get(turn).cash(Cards.get(cash1), Cards.get(cash2), Cards.get(cash3), Rewards, reward);
            Cards.addAll(Players.get(turn).returnedCards());
            reward++;
            if (reward == 17) {
                reward = 0;
            }
            place = true;
            view = false;
        }
    }

    private void stopViewingCards() {
        if (Players.get(Countries.get(select).getPossession()).getCardsN() < 5) {
            view = false;
            done = false;
        }
    }

    private void waitForDialog() {
        while (frame.isVisible()) {
            Thread.yield();
        }
    }




    public void mouseMoved(MouseEvent e) {
        if(!view){
            if (isActive){
                if (!Countries.get(active).giveBorder().contains(e.getX(), e.getY())) { //seeing if the mouse has left the border of a country.
                    if (isActive) {
                        isActive = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
            }
            else{
                for (int c = 0; c < 48; c++){
                    if (Countries.get(c).giveBorder().contains(e.getX(), e.getY())){ //seeing if the mouse has entered the border of a country
                        isActive = true;
                        active = c;
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        buffer();
                        return;
                    }
                }
                if (!Fortify.contains(e.getX(), e.getY())){ //seeing if the mouse has left the fortify button
                    if (f) {
                        f = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    f = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!Attack.contains(e.getX(), e.getY())){ //... if mouse has left attack button
                    if (a) {
                        a = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    a = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!View.contains(e.getX(), e.getY())){ //... if mouse has left view button
                    if (v) {
                        v = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    v = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!End.contains(e.getX(), e.getY())){ //... if mouse has left end button
                    if (end) {
                        end = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    end = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!Quit.contains(e.getX(), e.getY())){ //... if mouse has left quit button
                    if (quit) {
                        quit = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    quit = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
            }
        }
        if (view){
            if (isActive){
                if (!Players.get(turn).getCards().get(active).giveBorder().contains(e.getX(), e.getY())) { //if mouse has left the border of a card
                    if (isActive) {
                        isActive = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
            }
            else{
                for (int c = 0; c < Players.get(turn).getCardsN(); c++){
                    if (Players.get(turn).getCards().get(c).giveBorder().contains(e.getX(), e.getY())){ //if mouse has entered border of a card
                        isActive = true;
                        active = c;
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        buffer();
                        return;
                    }
                }
                if (!Done.contains(e.getX(), e.getY())){ //if mouse has left the done button
                    if (done) {
                        done = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    done = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!CashIn.contains(e.getX(), e.getY())){ //if mouse has left the cash button
                    if (cash) {
                        cash = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    cash = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
            }
        }
    }

    public void mouseDragged(MouseEvent e){
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    public void paint(Graphics g){
        g.drawImage(screen, 0, 0, null); //painting the buffered image
    }






    public void buffer() {
        Graphics2D graphic = screen.createGraphics();

        // Set rendering hints for anti-aliasing to improve graphics quality
        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Fill the entire screen with a black background
        graphic.setColor(Color.black);
        graphic.fillRect(0, 0, dim.width, dim.height);

        if (!view) {
            // Draw map and actions images
            graphic.drawImage(map, (int)((dim.getWidth()/1920.0) * 15), (int)((dim.getHeight()/1080.0) * 15), null);
            graphic.drawImage(actions, (int)((dim.getWidth()/1920.0) * 1615), (int)((dim.getHeight()/1080.0) * 15), null);

            if (isActive) {
                if (selected) {
                    // Highlight selected country if it neighbors the active country
                    if (Countries.get(select).isNeighbor(Countries.get(active))) {
                        graphic.drawImage(Countries.get(active).getSelected(), (int)((dim.getWidth()/1920.0) * 15), (int)((dim.getHeight()/1080.0) * 15), null);
                    } else {
                        graphic.drawImage(Countries.get(select).getSelected(), (int)((dim.getWidth()/1920.0) * 15), (int)((dim.getHeight()/1080.0) * 15), null);
                    }
                } else {
                    // Highlight active country
                    graphic.drawImage(Countries.get(active).getSelected(), (int)((dim.getWidth()/1920.0) * 15), (int)((dim.getHeight()/1080.0) * 15), null);
                }
            } else if (selected) {
                // Highlight selected country
                graphic.drawImage(Countries.get(select).getSelected(), (int)((dim.getWidth()/1920.0) * 15), (int)((dim.getHeight()/1080.0) * 15), null);
            }

            // Draw armies and player color on each country
            for (int c = 0; c < 48; c++) {
                graphic.setColor(Players.get(Countries.get(c).getPossession()).getColor());
                graphic.fillOval(Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - 10, Countries.get(c).giveBorder().y + Countries.get(c).giveBorder().height, 20, 20);

                graphic.setColor(Color.white);
                graphic.fillOval(Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - 6, Countries.get(c).giveBorder().y + Countries.get(c).giveBorder().height + 4, 12, 12);

                graphic.setFont(new Font("Urbana", Font.PLAIN, 12));
                graphic.setColor(Color.black);
                graphic.drawString(Countries.get(c).getArmies() + "", Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - 4, Countries.get(c).giveBorder().y + Countries.get(c).giveBorder().height + 15);
            }

            // Clear specific screen areas
            graphic.clearRect(0, 0, dim.width, (int)((dim.getHeight()/1080.0) * 17));
            graphic.clearRect(0, dim.height - (int)((dim.getHeight()/1080.0) * 17), dim.width, (int)((dim.getHeight()/1080.0) * 17));

            // Highlight custom buttons
            if (a) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 1635), (int)((dim.getHeight()/1080.0) * 350), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else if (v) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 1755), (int)((dim.getHeight()/1080.0) * 350), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else if (f) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 1635), (int)((dim.getHeight()/1080.0) * 450), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else if (end) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 1755), (int)((dim.getHeight()/1080.0) * 450), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else if (quit) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 1635), (int)((dim.getHeight()/1080.0) * 550), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else {
                graphic.drawImage(actions, (int)((dim.getWidth()/1920.0) * 1615), (int)((dim.getHeight()/1080.0) * 15), null);
            }

            // Draw custom buttons with different colors
            graphic.setColor(Color.blue);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 1645), (int)((dim.getHeight()/1080.0) * 360), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));
            graphic.setColor(Color.orange);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 1765), (int)((dim.getHeight()/1080.0) * 360), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));
            graphic.setColor(Color.red);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 1645), (int)((dim.getHeight()/1080.0) * 460), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));
            graphic.setColor(Color.pink);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 1765), (int)((dim.getHeight()/1080.0) * 460), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));
            graphic.setColor(Color.green);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 1645), (int)((dim.getHeight()/1080.0) * 560), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));

            // Draw button labels
            graphic.setFont(new Font("Urbana", Font.PLAIN, 14));
            graphic.drawString("Attack", (int)((dim.getWidth()/1920.0) * 1665), (int)((dim.getHeight()/1080.0) * 410));
            graphic.drawString("View Cards", (int)((dim.getWidth()/1920.0) * 1775), (int)((dim.getHeight()/1080.0) * 410));
            graphic.drawString("Fortify", (int)((dim.getWidth()/1920.0) * 1665), (int)((dim.getHeight()/1080.0) * 510));
            graphic.drawString("End turn", (int)((dim.getWidth()/1920.0) * 1785), (int)((dim.getHeight()/1080.0) * 510));
            graphic.drawString("Quit", (int)((dim.getWidth()/1920.0) * 1670), (int)((dim.getHeight()/1080.0) * 610));

            // Update the display based on the current game state
            updateGameState(graphic);
        }

        if (view) {
            displayCards(graphic);
        }

        repaint();
    }

    private void updateGameState(Graphics2D graphic) {
        if (income != 0) {
            // Inform the player they need to deploy more armies
            postInfo(Players.get(turn).getName() + " needs to deploy: " + income + " more armies.", graphic);
        } else if (selected && isActive && attack && select != active && Countries.get(active).getPossession() != turn && Countries.get(select).isNeighbor(Countries.get(active))) {
            postInfo("Initiate an attack on " + Countries.get(active).getName() + " by clicking.", graphic);
        } else if (moving) {
            postInfo("Troops selected in " + Countries.get(select).getName() + ". Move troops between " + Countries.get(fortify1).getName() + " and " + Countries.get(fortify2).getName(), graphic);
        } else if (!selected && fortify && fortify1 != -1 && fortify2 != -1) {
            postInfo("Ready to fortify from " + Countries.get(fortify1).getName() + " to " + Countries.get(fortify2).getName(), graphic);
        } else if (selected && isActive && fortify && select != active && Countries.get(active).getPossession() == turn && Countries.get(select).isNeighbor(Countries.get(active))) {
            postInfo("Prepare to strengthen " + Countries.get(active).getName() + " from " + Countries.get(select).getName() + ".", graphic);
        } else if (selected && fortify) {
            postInfo("Country selected: " + Countries.get(select).getName() + ". Select an attack target.", graphic);
        } else if (!selected && fortify) {
            postInfo("Choose a country to fortify from.", graphic);
        } else if (selected && attack) {
            postInfo("Country selected: " + Countries.get(select).getName() + ". Select an attack target.", graphic);
        } else if (!selected && attack) {
            postInfo("Choose a country to launch an attack from.", graphic);
        } else {
            postInfo("Decide to attack, fortify, or conclude your turn.", graphic);
        }

    }




    private void displayCards(Graphics2D graphic) {
        if (Players.get(turn).getCardsN() == 0) {
            graphic.setColor(Color.white);
            graphic.setFont(new Font("Urbana", Font.PLAIN, 12));
            graphic.drawString("You have no cards", dim.width/2 - 300, dim.height/2 + 50);
        } else {
            highlightCards(graphic);

            for (int c = 0; c < Players.get(turn).getCardsN(); c++) {
                if (c < 5) {
                    graphic.drawImage(Players.get(turn).getCards().get(c).getCard(), (int)((dim.getWidth()/1920.0) * 160 + (c*350)), (int)((dim.getHeight()/1080.0) * 120), null);
                    Players.get(turn).getCards().get(c).setBorder((int)((dim.getWidth()/1920.0) * 160 + (c*350)), (int)((dim.getHeight()/1080.0) * 120), (int)((dim.getWidth()/1920.0) * 220), (int)((dim.getHeight()/1080.0) * 320));
                } else {
                    graphic.drawImage(Players.get(turn).getCards().get(c).getCard(), (int)((dim.getWidth()/1920.0) * 160 + ((c-5)*350)), (int)((dim.getHeight()/1080.0) * 540), null);
                    Players.get(turn).getCards().get(c).setBorder((int)((dim.getWidth()/1920.0) * 160 + ((c-5)*350)), (int)((dim.getHeight()/1080.0) * 540), (int)((dim.getWidth()/1920.0) * 220), (int)((dim.getHeight()/1080.0) * 320));
                }
            }

            highlightSelectedCards(graphic);

            if (done) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 1050), (int)((dim.getHeight()/1080.0) * 890), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else {
                graphic.clearRect((int)((dim.getWidth()/1920.0) * 1050), (int)((dim.getHeight()/1080.0) * 890), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            }

            if (cash) {
                graphic.setColor(Color.yellow);
                graphic.fillRect((int)((dim.getWidth()/1920.0) * 750), (int)((dim.getHeight()/1080.0) * 890), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            } else {
                graphic.clearRect((int)((dim.getWidth()/1920.0) * 750), (int)((dim.getHeight()/1080.0) * 890), (int)((dim.getWidth()/1920.0) * 120), (int)((dim.getHeight()/1080.0) * 100));
            }

            graphic.setColor(Color.green);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 760), (int)((dim.getHeight()/1080.0) * 900), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));

            graphic.setColor(Color.red);
            graphic.fillRect((int)((dim.getWidth()/1920.0) * 1060), (int)((dim.getHeight()/1080.0) * 900), (int)((dim.getWidth()/1920.0) * 100), (int)((dim.getHeight()/1080.0) * 80));

            graphic.setColor(Color.black);
            graphic.setFont(new Font("Urbana", Font.PLAIN, (int)((dim.getWidth()/1920.0) * 20)));
            graphic.drawString("Cash In", (int)((dim.getWidth()/1920.0) * 780), (int)((dim.getHeight()/1080.0) * 950));
            graphic.drawString("Done", (int)((dim.getWidth()/1920.0) * 1080), (int)((dim.getHeight()/1080.0) * 950));
        }
    }

    private void highlightCards(Graphics2D graphic) {
        if (isActive) {
            graphic.setColor(Color.yellow);
            Rectangle temp = new Rectangle(Players.get(turn).getCards().get(active).giveBorder());
            temp.grow((int)((dim.getWidth()/1920.0) * 10), (int)((dim.getHeight()/1920.0) * 10));
            graphic.fill(temp);
        }
    }

    private void highlightSelectedCards(Graphics2D graphic) {
        highlightSelectedCard(cash1, graphic);
        highlightSelectedCard(cash2, graphic);
        highlightSelectedCard(cash3, graphic);
    }

    private void highlightSelectedCard(int cardIndex, Graphics2D graphic) {
        if (cardIndex != -1) {
            graphic.setColor(Color.yellow);
            Rectangle temp = new Rectangle(Players.get(turn).getCards().get(cardIndex).giveBorder());
            temp.grow((int)((dim.getWidth()/1920.0) * 10), (int)((dim.getHeight()/1920.0) * 10));
            graphic.fill(temp);
        }
    }




    public static BufferedImage resize(Image image, int width, int height) { //code found from internet. supposedly resizes images
        return Card.resize(image, width, height);
    }





    public void postInfo(String text, Graphics2D graphics) {
        graphics.setFont(new Font("English", Font.PLAIN, 15));
        graphics.setColor(Color.black);
    
        int maxWidth = 25; // Maximum characters per line
        int lineHeight = 20; // Pixel height difference between lines
        int baseX = (int)((dim.getWidth() / 1920.0) * 1675); // Base X position
        int baseY = (int)((dim.getHeight() / 1080.0) * 815); // Base Y position
    
        if (text.length() <= maxWidth) {
            graphics.drawString(text, baseX, baseY);
        } else {
            int length = text.length();
            int start = 0;
            int line = 0;
    
            while (start < length) {
                int end = start + maxWidth;
                if (end > length) end = length;
    
                // Move back to the nearest space (if not the end of the string)
                if (end < length) {
                    while (end > start && text.charAt(end) != ' ') {
                        end--;
                    }
                }
    
                // Draw substring from start to end
                graphics.drawString(text.substring(start, end), baseX, baseY + (line * lineHeight));
                start = end + 1; // Skip the space
                line++;
            }
        }
    }


    
}