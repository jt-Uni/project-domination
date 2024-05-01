package core;

import aiEngine.AIPlayer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;





public class StartScreen extends JFrame implements ActionListener {
    public Object Player1;
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

    public StartScreen() {
        super("World Conquest");
        setupUI();
        setResizable(false);
        setVisible(true);
    }

    private void setupUI() {
        setSize(600, 900);
        setLayout(new BorderLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 390, dim.height / 2 - 500);

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
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(280, 50));
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
            JPanel playerRow = new JPanel(new GridLayout(1, 6, 5, 0));
            playerRow.add(new JLabel("Name:"));
            playerRow.add(playerNames[i]);
            playerRow.add(new JLabel("Type:"));
            playerRow.add(playerTypes[i]);
            playerRow.add(new JLabel("Color:"));
            playerRow.add(colorCombos[i]);

            if (playerTypes[i].getSelectedIndex() == 1) {
                playerRow.add(new JLabel("Difficulty:"));
                playerRow.add(difficultyCombos[i]);
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

        // Available colors for random assignment
        Set<Color> availableColors = new HashSet<>(Arrays.asList(
                Color.red, Color.blue, Color.green, Color.yellow, Color.black, Color.gray
        ));

        for (int i = 0; i < 6; i++) {
            String name = playerNames[i].getText();
            Color color = getColorFromCombo(colorCombos[i]);

            if (name.isEmpty() || (playerTypes[i].getSelectedIndex() == 0 && color == null)) continue;

            if (playerTypes[i].getSelectedIndex() == 1) { // AI player
                String difficulty = difficulties[difficultyCombos[i].getSelectedIndex()];

                // Randomly assign a color if none specified
                if (color == null) {
                    color = availableColors.toArray(new Color[0])[new Random().nextInt(availableColors.size())];
                    availableColors.remove(color); // Remove assigned color from pool
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

        if (!validatePlayers()) {
            return;
        }

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
}
