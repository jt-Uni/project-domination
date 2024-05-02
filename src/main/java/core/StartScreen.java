package core;

import aiEngine.AIPlayer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

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

    private void setupPanels() {
        playersPanel = new JPanel(new GridLayout(7, 1));
        playersPanel.setPreferredSize(new Dimension(400, 400));
        playersPanel.setBackground(new Color(64, 224, 208)); // Turquoise background
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(280, 50));
        bottomPanel.setBackground(new Color(64, 224, 208)); // Turquoise background
    }

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

    private void setupMouseClickClearing(JTextField field) {
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                field.setText("");
            }
        });
    }

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





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            setupPlayers();
        }
    }

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

    private void showError(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

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

    public boolean started() {
        return start;
    }

    public ArrayList<Player> getPlayers() {
        return users;
    }




    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Convert to Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale the Image
        return new ImageIcon(scaledImage); // Convert back to ImageIcon
    }






    // Revised PlayerTypeChangeListener with resizing
    private class PlayerTypeChangeListener implements ActionListener {
        private int index;
        private Map<Integer, ImageIcon> typeIcons;

        public PlayerTypeChangeListener(int index) {
            this.index = index;
            typeIcons = new HashMap<>();

            typeIcons.put(0, resizeIcon(new ImageIcon("./assets/1.png"), 20, 20)); // Resize immediately upon mapping
            typeIcons.put(1, resizeIcon(new ImageIcon("./assets/2.png"), 20, 20)); // Adjust these paths as needed
        }

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
