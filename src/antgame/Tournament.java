package antgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * A class representing the tournament ability of the AntGame
 * 
 * @author Team 13
 */
public class Tournament extends JFrame {

    private List<List<List<String>>> antBrains = new ArrayList<>();
    private List<String> teamNames = new ArrayList<>();
    private List<Score> scores = new ArrayList<>();
    private World newWorld = null;
    private PlayGame newGame;
    private int winner;
    JTextArea scoreField;
    JTextArea worldField;

    /**
     * Constructor of the tournament class. Builds the UI for tournament game
     */
    public Tournament() {
        super("Tournament");
        buildUI();
    }

    /**
     * Uses the methods to add components to the container
     */
    private void buildUI() {
        Container c;
        c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(createUsersInfoAndStart(), BorderLayout.WEST);
        c.add(createWorldField(), BorderLayout.CENTER);
        c.add(createScoreField(), BorderLayout.EAST);
        c.setPreferredSize(new Dimension(1200, 900));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Creates the panel for the users which includes buttons to upload
     * ant-brains and world if users want to and a button to start the game and returns it.
     *
     * @return JPanel including label with upload button, start button and upload world button
     */
    private JPanel createUsersInfoAndStart() {
        JPanel outerPanel, userInfo;
        JLabel lbl;
        JButton uploadBtn, startBtn, uploadWorld;

        userInfo = new JPanel();
        outerPanel = new JPanel();
        lbl = new JLabel("Upload the ant-brains");
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        startBtn = new JButton("Start Game");
        startBtn.setBorderPainted(false);
        startBtn.setFocusPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        startBtn.addMouseListener(new MouseAdapter() {
            Font f = startBtn.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                startBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                startBtn.setFont(f);
            }
        });
        startBtn.addActionListener(new ActionListener() {
            /**
             * handles the event when the button is clicked.
             *
             * @param e the event the table has been clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int prevScore;
                String winnerTeam;
                //checks whether have been uploaded more than 1 ant-brains
                if (antBrains.size() > 1) {
                    //checks if world has been uploaded if not
                    // load a random one
                    if (newWorld == null) {
                        newWorld = new World(150, 150);
                        worldField.setText(newWorld.testWorld());
                        JOptionPane.showMessageDialog(rootPane, "A random world will be used");
                    }
                    //for loops that make every team play each other 2 times
                    //and update scores
                    for (int i = 0; i < antBrains.size(); i++) {
                        for (int j = 0; j < antBrains.size(); j++) {
                            if (i != j) {
                                JOptionPane.showMessageDialog(rootPane, "Next match: " + teamNames.get(i) + " vs " + teamNames.get(j));
                                newGame = new PlayGame(antBrains.get(i), antBrains.get(j), newWorld);
                                winner = newGame.runGame();
                                if (winner == 0) {
                                    teamScoreUpdate(i);
                                    JOptionPane.showMessageDialog(rootPane, teamNames.get(i) + "Red team wins!");
                                } else if (winner == 2) {
                                    teamScoreUpdate(j);
                                    JOptionPane.showMessageDialog(rootPane, teamNames.get(j) + " wins!");
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Draw!");
                                }
                                scoreFieldUpdate();
                            }
                        }
                        Collections.sort(scores);
                    }
                } else {
                    System.out.println();
                    JOptionPane.showMessageDialog(rootPane, "More than 1 ant-brains should be uploaded!");
                }
            }
        }
        );

        uploadBtn = new JButton("Upload ant-brain");
        uploadBtn.setBorderPainted(false);
        uploadBtn.setFocusPainted(false);
        uploadBtn.setContentAreaFilled(false);
        uploadBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        uploadBtn.addMouseListener(new MouseAdapter() {
            Font f = uploadBtn.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                uploadBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                uploadBtn.setFont(f);
            }
        });
        uploadBtn.addActionListener(new ActionListener() {
            /**
             * handles the event when the button is clicked. Opens a file
             * chooser window and adds text of the file into array list.
             *
             * @param e the event the table has been clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                String teamName;
                int v = chooser.showOpenDialog(null);
                if (v == JFileChooser.APPROVE_OPTION) {
                    File antBrain = chooser.getSelectedFile();
                    AntBrainParser ant = new AntBrainParser();
                    teamName = chooser.getSelectedFile().getName();
                    boolean exists = false;
                    //checks whether the name of the new ant-brain inserted
                    //already exists
                    for (int i = 0; i < teamNames.size(); i++) {
                        if (teamNames.get(i).equals(teamName)) {
                            exists = true;
                        }
                    }
                    //if there is not ant-brain file with same name
                    // add it to the list. Also the name of the file is added to a list
                    // and score too
                    if (!exists) {
                        antBrains.add(ant.AntBrainParser(antBrain));
                        teamNames.add(teamName);
                        scores.add(new Score(teamName, 0));
                        scoreFieldUpdate();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "The name of your ant-brain already exists.\nPlease rename the file of your ant-brain!(" + teamName + ")");
                    }
                }
            }
        });
        
        uploadWorld = new JButton("Upload World");
        uploadWorld.setBorderPainted(false);
        uploadWorld.setFocusPainted(false);
        uploadWorld.setContentAreaFilled( false);
        uploadWorld.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        /**
         * creates effect when hovering button
         */
        uploadWorld.addMouseListener(
                new MouseAdapter() {
                    Font f = uploadWorld.getFont();

                    /**
                     * makes the text of the button bold when mouse enters the
                     * area of the button
                     *
                     * @param me the event that mouse entered button area
                     */
                    @Override
                    public void mouseEntered(MouseEvent me
                    ) {
                        uploadWorld.setFont(new Font("Times New Roman", Font.BOLD, 18));
                    }

                    /**
                     * sets the font of the button as it was before entering the
                     * area button
                     *
                     * @param me the event that mouse left button area
                     */
                    @Override
                    public void mouseExited(MouseEvent me
                    ) {
                        uploadWorld.setFont(f);
                    }
                }
        );
        uploadWorld.addActionListener(
                new ActionListener() {
                    /**
                     * handles the event when the button is clicked. Opens a
                     * file chooser window and adds text of the file into array
                     * list.
                     *
                     * @param e the event the table has been clicked.
                     */
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        JFileChooser chooser = new JFileChooser();
                        int v = chooser.showOpenDialog(null);
                        if (v == JFileChooser.APPROVE_OPTION) {
                            File worldUploaded = chooser.getSelectedFile();
                            WorldParser wrldParser = new WorldParser();
                            try {
                                //pass the world uploaded to the parser
                                //and if it's valid it is return to the newWorld variable
                                newWorld = wrldParser.WorldParser(worldUploaded);
                                //set the world to the textarea so it can be seen
                                //by the users
                                worldField.setText(newWorld.testWorld());
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(SingleMatch.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
        );

        outerPanel.setLayout(new BorderLayout());
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.PAGE_AXIS));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadWorld.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfo.add(Box.createRigidArea(new Dimension(0, 20)));
        userInfo.add(lbl);
        userInfo.add(uploadBtn);
        userInfo.add(Box.createRigidArea(new Dimension(0, 80)));
        userInfo.add(uploadWorld);
        outerPanel.add(userInfo, BorderLayout.NORTH);
        outerPanel.add(startBtn, BorderLayout.SOUTH);
        outerPanel.setPreferredSize(new Dimension(200, 0));

        return outerPanel;
    }

    /**
     * Creates the world text area and puts it into a scroll pane
     * @return JScrollPane including the world text area
     */
    private JScrollPane createWorldField() {
        JPanel outerPanel;
        JScrollPane scroll;

        outerPanel = new JPanel();
        worldField = new JTextArea();

        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        worldField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        scroll = new JScrollPane(worldField);
        scroll.setPreferredSize(new Dimension(800, 0));
        scroll.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Ant-World"));
        scroll.setPreferredSize(new Dimension(700, 0));

        return scroll;
    }

    /**
     * Creates the text area for scores and returns it
     * @return JTextArea for scores
     */
    private JTextArea createScoreField(){        
        scoreField = new JTextArea();
        scoreField.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Score"));
        scoreField.setPreferredSize(new Dimension(200, 0));
        
        return scoreField;
    }
    /**
     * Updates text of the score field based on the new scores.
     */
    private void scoreFieldUpdate() {
        scoreField.setText(null);
        for (int i = 0; i < scores.size(); i++) {
            scoreField.append(scores.get(i).getTeamName() + "\t\t" + scores.get(i).getScore() + "\n");
        }
    }

    /**
     * updates the score of a selected team which is the winner of a game
     * @param i index of the team in the score list
     */
    private void teamScoreUpdate(int i) {
        scores.get(i).setScore(scores.get(i).getScore() + 1);
    }
}