/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Constructs the UI for a single match game
 *
 * @author ADigenis
 */
public class SingleMatch extends JFrame {

    private List<List<String>> antBrainTeam1;
    private List<List<String>> antBrainTeam2;
    private World newWorld = new World(50, 50);
    private PlayGame newGame;
    private int winner;

    /**
     * Constructor of the single match. Builds the UI for single match
     */
    public SingleMatch() {
        super("Single Match");
        buildUI();
    }

    /**
     * Adds users buttons and the world field the container
     */
    private void buildUI() {
        Container c;
        c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(createUsersInfoAndStart(), BorderLayout.WEST);

        c.add(createWorldField(), BorderLayout.CENTER);
        c.setPreferredSize(new Dimension(1200, 900));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Creates the panel for the users which includes buttons to upload
     * ant-brains and a button to start the game and returns it.
     *
     * @return JPanel including users button and start game button
     */
    private JPanel createUsersInfoAndStart() {
        JPanel outerPanel, userInfo;
        JLabel team1, team2;
        JButton uploadTeam1, uploadTeam2, startBtn;

        userInfo = new JPanel();
        outerPanel = new JPanel();
        team1 = new JLabel("Red Team");
        team1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        team2 = new JLabel("Black Team");
        team2.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        startBtn = new JButton("Start Game");
        startBtn.setBorderPainted(false);
        startBtn.setFocusPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        /**
         * creates effect when hovering over the button
         */
        startBtn.addMouseListener(new MouseAdapter() {
            Font f = startBtn.getFont();

            /**
             * makes the text of the button bold when mouse enters the area of
             * the button
             *
             * @param me the event that mouse entered button area
             */
            @Override
            public void mouseEntered(MouseEvent me) {
                startBtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
            }

            /**
             * sets the font of the button as it was before entering the area
             * button
             *
             * @param me the event that mouse left button area
             */
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
                if (antBrainTeam1 != null && antBrainTeam2 != null && newWorld != null) {
                    newGame = new PlayGame(antBrainTeam1, antBrainTeam2, newWorld);
                    winner = newGame.runGame();
                    if (winner == 0) {
                        JOptionPane.showMessageDialog(rootPane, "Red team wins!");
                    } else if (winner == 1) {
                        JOptionPane.showMessageDialog(rootPane, "Black team wins!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Draw!");
                    }
                } else {
                    System.out.println();
                    JOptionPane.showMessageDialog(rootPane, "Ant-Brains were not uploaded!");
                }
            }
        });

        uploadTeam1 = new JButton("Upload ant-brain");
        uploadTeam1.setBorderPainted(false);
        uploadTeam1.setFocusPainted(false);
        uploadTeam1.setContentAreaFilled(false);
        uploadTeam1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        /**
         * creates effect when hovering button
         */
        uploadTeam1.addMouseListener(new MouseAdapter() {
            Font f = uploadTeam1.getFont();

            /**
             * makes the text of the button bold when mouse enters the area of
             * the button
             *
             * @param me the event that mouse entered button area
             */
            @Override
            public void mouseEntered(MouseEvent me) {
                uploadTeam1.setFont(new Font("Times New Roman", Font.BOLD, 18));
            }

            /**
             * sets the font of the button as it was before entering the area
             * button
             *
             * @param me the event that mouse left button area
             */
            @Override
            public void mouseExited(MouseEvent me) {
                uploadTeam1.setFont(f);
            }
        });
        uploadTeam1.addActionListener(new ActionListener() {
            /**
             * handles the event when the button is clicked. Opens a file
             * chooser window and adds text of the file into array list.
             *
             * @param e the event the table has been clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int v = chooser.showOpenDialog(null);
                if (v == JFileChooser.APPROVE_OPTION) {
                    File antBrain = chooser.getSelectedFile();
                    AntBrainParser ant = new AntBrainParser();
                    antBrainTeam1 = ant.AntBrainParser(antBrain);
                }
            }
        });

        uploadTeam2 = new JButton("Upload ant-brain");
        uploadTeam2.setBorderPainted(false);
        uploadTeam2.setFocusPainted(false);
        uploadTeam2.setContentAreaFilled(false);
        uploadTeam2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        /**
         * creates effect when hovering button
         */
        uploadTeam2.addMouseListener(new MouseAdapter() {
            Font f = uploadTeam2.getFont();

            /**
             * makes the text of the button bold when mouse enters the area of
             * the button
             *
             * @param me the event that mouse entered button area
             */
            @Override
            public void mouseEntered(MouseEvent me) {
                uploadTeam2.setFont(new Font("Times New Roman", Font.BOLD, 18));
            }

            /**
             * sets the font of the button as it was before entering the area
             * button
             *
             * @param me the event that mouse left button area
             */
            @Override
            public void mouseExited(MouseEvent me) {
                uploadTeam2.setFont(f);
            }
        });
        uploadTeam2.addActionListener(new ActionListener() {
            /**
             * handles the event when the button is clicked. Opens a file
             * chooser window and adds text of the file into array list.
             *
             * @param e the event the table has been clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int v = chooser.showOpenDialog(null);
                if (v == JFileChooser.APPROVE_OPTION) {
                    File antBrain = chooser.getSelectedFile();
                    AntBrainParser ant = new AntBrainParser();
                    antBrainTeam2 = ant.AntBrainParser(antBrain);
                }
            }
        });
        outerPanel.setLayout(new BorderLayout());
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.PAGE_AXIS));
        team1.setAlignmentX(Component.CENTER_ALIGNMENT);
        team2.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadTeam1.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadTeam2.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfo.add(Box.createRigidArea(new Dimension(0, 20)));
        userInfo.add(team1);
        userInfo.add(uploadTeam1);
        userInfo.add(Box.createRigidArea(new Dimension(0, 40)));
        userInfo.add(team2);
        userInfo.add(uploadTeam2);

        outerPanel.add(userInfo, BorderLayout.NORTH);
        outerPanel.add(startBtn, BorderLayout.SOUTH);
        outerPanel.setPreferredSize(new Dimension(250, 0));

        return outerPanel;
    }

    /**
     * Creates a panel with a jtextarea with world printed
     *
     * @return JPanel including a JTextArea
     */
    public JPanel createWorldField() {
        JTextArea worldField;
        JPanel outerPanel;

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        outerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Ant-World"));
        //prints the world into the text area
        worldField = new JTextArea(newWorld.testWorld());
        //set font to fix spacing
        worldField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outerPanel.add(worldField);

        return outerPanel;
    }
}
