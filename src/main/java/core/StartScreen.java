/*
 * Rafik deboub
 * Sussex university
 * 2023/2024
 */

package core;

import aiEngine.AIPlayer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;




/**
 * StartScreen is a GUI window for setting up a World Conquest game.
 * It allows users to define players, choose their types, colors, and difficulties.
 */
public class StartScreen extends JFrame implements ActionListener {
    private Dimension dim;
    private JPanel playersPanel, bottomPanel;
    public JTextField[] playerNames;
    public JComboBox<String>[] playerTypes;
    public JComboBox<String>[] colorCombos;
    public JComboBox<String>[] difficultyCombos;
    private String[] colors = {"Empty", "Red", "Blue", "Green", "Yellow", "Black", "Gray"};
    private String[] playerTypesArr = {"Human", "AI"};
    private String[] difficulties = {"Easy", "Hard", "Extremely Hard"};
    private ArrayList<Player> users;
    private boolean start;

    private Map<Integer, ImageIcon> typeIcons;




    /**
     * Constructs a new StartScreen window for setting up a World Conquest game.
     * Initializes the GUI components and displays the window.
     */

    public StartScreen() {
        super("World Conquest");

        // Initialize icons
        typeIcons = new HashMap<>();
        typeIcons.put(0, new ImageIcon("./assets/1.png")); // Human icon path
        typeIcons.put(1, new ImageIcon("./assets/2.png")); // AI icon path

        setupUI();
        setResizable(false);
        setVisible(true);

    }



    /**
     * Sets up the main UI components for the start screen, including panels and fields.
     */

    private void setupUI() {
        setSize(800, 950);
        setLayout(new BorderLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 450, dim.height / 2 - 600);

        setupPanels();
        setupPlayerFields();
        addComponentsToPanels();

        add("North", new ScreenLogo());
        add("Center", playersPanel);
        add("South", bottomPanel);
    }


    /**
     * Configures the panels for player setup and the start button.
     */

    private void setupPanels() {
        playersPanel = new JPanel(new GridLayout(7, 1));
        playersPanel.setPreferredSize(new Dimension(400, 400));
        playersPanel.setBackground(new Color(64, 224, 208)); // Turquoise background
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(280, 50));
        bottomPanel.setBackground(new Color(64, 224, 208)); // Turquoise background
    }


    /**
     * Sets up the fields for player names, types, colors, and difficulties.
     */

    private void setupPlayerFields() {
        playerNames = new JTextField[6];
        colorCombos = new JComboBox[6];
        playerTypes = new JComboBox[6];
        difficultyCombos = new JComboBox[6];

        for (int i = 0; i < 6; i++) {
            playerNames[i] = new JTextField("Player " + (i + 1));
            setupMouseClickClearing(playerNames[i]);

            colorCombos[i] = new JComboBox<>(colors);
            playerTypes[i] = new JComboBox<>(playerTypesArr);
            difficultyCombos[i] = new JComboBox<>(difficulties);

            playerTypes[i].addActionListener(new PlayerTypeChangeListener(i));
        }
    }


    /**
     * Sets up a mouse click listener to clear the text field on press.
     * @param field The text field to be cleared on click.
     */

    private void setupMouseClickClearing(JTextField field) {
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                field.setText("");
            }
        });
    }


    /**
     * Adds the necessary components to each player setup row and to the main panel.
     */

    private void addComponentsToPanels() {
        for (int i = 0; i < 6; i++) {
            JPanel playerRow = new JPanel(new GridBagLayout());
            playerRow.setBackground(new Color(224, 240, 200)); // Light pistachio background
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0; gbc.gridy = 0;
            playerRow.add(new JLabel("Name:"), gbc);
            gbc.gridx = 1;
            playerRow.add(playerNames[i], gbc);

            gbc.gridx = 2; gbc.gridy = 0;
            playerRow.add(new JLabel("Type:"), gbc);
            gbc.gridx = 3;
            playerRow.add(playerTypes[i], gbc);

            gbc.gridx = 4;
            ImageIcon icon = typeIcons.get(playerTypes[i].getSelectedIndex());
            if (icon != null) {
                icon = resizeIcon(icon, 23, 23); // Resize the icon
                JLabel iconLabel = new JLabel(icon);
                playerRow.add(iconLabel, gbc);
            }

            gbc.gridx = 5;
            playerRow.add(new JLabel("Color:"), gbc);
            gbc.gridx = 6;
            colorCombos[i].setBackground(new Color(224, 240, 200)); // Light pistachio combo box
            playerRow.add(colorCombos[i], gbc);

            if (playerTypes[i].getSelectedIndex() == 1) {
                gbc.gridx = 7;
                playerRow.add(new JLabel("Difficulty:"), gbc);
                gbc.gridx = 8;
                difficultyCombos[i].setBackground(new Color(224, 240, 200)); // Light pistachio combo box
                playerRow.add(difficultyCombos[i], gbc);
            }

            playersPanel.add(playerRow);
        }

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);
        bottomPanel.add(startButton);
    }




    /**
     * Handles button clicks on the start screen.
     * @param e The ActionEvent triggered by a button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            setupPlayers();
        }
    }


    /**
     * Initializes the player list based on the selected player types and attributes.
     */

    public void setupPlayers() {
        users = new ArrayList<>();

        Set<Color> availableColors = new HashSet<>(Arrays.asList(
                Color.red, Color.blue, Color.green, Color.yellow, Color.black, Color.gray
        ));

        for (int i = 0; i < 6; i++) {
            String name = playerNames[i].getText();
            Color color = getColorFromCombo(colorCombos[i]);

            if (name.isEmpty() || (playerTypes[i].getSelectedIndex() == 0 && color == null)) continue;

            if (playerTypes[i].getSelectedIndex() == 1) { // AI player
                String difficulty = difficulties[difficultyCombos[i].getSelectedIndex()];

                if (color == null) {
                    color = availableColors.toArray(new Color[0])[new Random().nextInt(availableColors.size())];
                    availableColors.remove(color);
                }

                users.add(new AIPlayer(name, users.size(), color, difficulty));
            } else { // Human player
                users.add(new Player(name, users.size(), color));
            }
        }

        if (users.size() < 2) {
            showError("At least 2 players are needed!");
            return;
        }

        if (!validatePlayers()) return;

        start = true;
        setVisible(false);
    }



    /**
     * Validates the list of players, checking for unique names and colors.
     * @return True if all players have unique names and colors, false otherwise.
     */

    private boolean validatePlayers() {
        Set<String> names = new HashSet<>();
        Set<Color> colors = new HashSet<>();

        for (Player player : users) {
            String name = player.getName();
            Color color = player.getColor();

            if (names.contains(name)) {
                showError("Duplicate name found: " + name);
                return false;
            }

            if (colors.contains(color)) {
                showError("Duplicate color found: " + player.getColorName());
                return false;
            }

            names.add(name);
            colors.add(color);
        }

        return true;
    }


    /**
     * Displays an error message dialog.
     * @param message The error message to display.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Converts a JComboBox selection into a corresponding Color instance.
     * @param combo The JComboBox containing the color selection.
     * @return The corresponding Color instance, or null if no valid color is selected.
     */
    private Color getColorFromCombo(JComboBox<String> combo) {
        return switch (combo.getSelectedIndex()) {
            case 1 -> Color.red;
            case 2 -> Color.blue;
            case 3 -> Color.green;
            case 4 -> Color.yellow;
            case 5 -> Color.black;
            case 6 -> Color.gray;
            default -> null;
        };
    }



    /**
     * Checks if the start screen setup is complete and the game can start.
     * @return True if setup is complete and the game can start, false otherwise.
     */
    public boolean started() {
        return start;
    }


    /**
     * Retrieves the list of players defined by the start screen setup.
     * @return An ArrayList of Player instances.
     */
    public ArrayList<Player> getPlayers() {
        return users;
    }




    /**
     * Resizes an ImageIcon to the specified dimensions.
     * @param icon The original ImageIcon.
     * @param width The desired width.
     * @param height The desired height.
     * @return A new ImageIcon with the specified dimensions.
     */

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Convert to Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale the Image
        return new ImageIcon(scaledImage); // Convert back to ImageIcon
    }






    /**
     * A listener class for changes to the player type JComboBox, with immediate resizing.
     */
    // Revised PlayerTypeChangeListener with resizing
    private class PlayerTypeChangeListener implements ActionListener {
        private int index;
        private Map<Integer, ImageIcon> typeIcons;


        /**
         * Constructs a new PlayerTypeChangeListener for the specified player row index.
         * @param index The index of the player row.
         */
        public PlayerTypeChangeListener(int index) {
            this.index = index;
            typeIcons = new HashMap<>();

            typeIcons.put(0, resizeIcon(new ImageIcon("./assets/1.png"), 20, 20)); // Resize immediately upon mapping
            typeIcons.put(1, resizeIcon(new ImageIcon("./assets/2.png"), 20, 20)); // Adjust these paths as needed
        }


        /**
         * Handles the ActionEvent triggered by changing the player type.
         * @param e The ActionEvent object.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel playerRow = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Populate the row with updated components
            gbc.gridx = 0; gbc.gridy = 0;
            playerRow.add(new JLabel("Name:"), gbc);
            gbc.gridx = 1;
            playerRow.add(playerNames[index], gbc);

            gbc.gridx = 2;
            playerRow.add(new JLabel("Type:"), gbc);
            gbc.gridx = 3;
            playerRow.add(playerTypes[index], gbc);

            gbc.gridx = 4;
            JLabel iconLabel = new JLabel(typeIcons.get(playerTypes[index].getSelectedIndex())); // Use the resized icons
            playerRow.add(iconLabel, gbc);

            gbc.gridx = 5;
            playerRow.add(new JLabel("Color:"), gbc);
            gbc.gridx = 6;
            playerRow.add(colorCombos[index], gbc);

            if (playerTypes[index].getSelectedIndex() == 1) {
                gbc.gridx = 7;
                playerRow.add(new JLabel("Difficulty:"), gbc);
                gbc.gridx = 8;
                playerRow.add(difficultyCombos[index], gbc);
            }

            playersPanel.remove(index);
            playersPanel.add(playerRow, index);
            playersPanel.revalidate();
        }
    }

}
