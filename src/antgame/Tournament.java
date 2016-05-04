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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ADigenis
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

    public Tournament() {
        super("Tournament");
        buildUI();
    }

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

    private JPanel createUsersInfoAndStart() {
        JPanel outerPanel, userInfo;
        JLabel team1;
        JButton uploadBtn, startBtn;

        userInfo = new JPanel();
        outerPanel = new JPanel();
        team1 = new JLabel("Upload the ant-brains");
        team1.setFont(new Font("Times New Roman", Font.PLAIN, 18));

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
                if (antBrains.size() > 1) {
                    if (newWorld == null) {
                        newWorld = new World(50, 50);
                        worldField.setText(newWorld.testWorld());
                        JOptionPane.showMessageDialog(rootPane, "A random world will be used");
                    }
                    for (int i = 0; i < antBrains.size(); i++) {
                        for (int j = 0; j < antBrains.size(); j++) {
                            if (i != j) {
                                JOptionPane.showMessageDialog(rootPane, "Next match: " + teamNames.get(i) + " vs " + teamNames.get(j));
                                newGame = new PlayGame(antBrains.get(i), antBrains.get(j), newWorld);
                                winner = newGame.runGame();
                                if (winner == 0) {
                                    teamScoreUpdate(i);
                                    JOptionPane.showMessageDialog(rootPane, teamNames.get(i) + "Red team wins!");
                                } else if (winner == 1) {
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

        uploadBtn = new JButton("Upload ant-brains");
        uploadBtn.setBorderPainted(false);
        uploadBtn.setFocusPainted(false);
        uploadBtn.setContentAreaFilled(false);
        uploadBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        uploadBtn.addMouseListener(new MouseAdapter() {
            Font f = uploadBtn.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                uploadBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
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
                    for (int i = 0; i < teamNames.size(); i++) {
                        if (teamNames.get(i).equals(teamName)) {
                            exists = true;
                        }
                    }
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

        outerPanel.setLayout(new BorderLayout());
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.PAGE_AXIS));
        team1.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfo.add(Box.createRigidArea(new Dimension(0, 20)));
        userInfo.add(team1);
        userInfo.add(uploadBtn);
        userInfo.add(Box.createRigidArea(new Dimension(0, 40)));

        outerPanel.add(userInfo, BorderLayout.NORTH);
        outerPanel.add(startBtn, BorderLayout.SOUTH);
        outerPanel.setPreferredSize(new Dimension(200, 0));

        return outerPanel;
    }

    public JPanel createWorldField() {
        JPanel outerPanel;
        JPanel innerPanel;
        JScrollPane scroll;

        innerPanel = new JPanel();
        outerPanel = new JPanel();
        scoreField = new JTextArea();
        worldField = new JTextArea();

        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        innerPanel.add(worldField);
        //set font to fix spacing
        worldField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        scroll = new JScrollPane(worldField);
        scroll.setPreferredSize(new Dimension(700, 0));
        scroll.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Ant-World"));
        outerPanel.add(scroll);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        innerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Score"));
        innerPanel.add(scoreField);
        outerPanel.add(innerPanel);

        return outerPanel;
    }

    private void scoreFieldUpdate() {
        scoreField.setText(null);
        for (int i = 0; i < scores.size(); i++) {
            scoreField.append(scores.get(i).getTeamName() + "\t\t" + scores.get(i).getScore() + "\n");
        }
    }

    private void teamScoreUpdate(int i) {
//        int newScore;
//        newScore = scores.get(i).getScore() + 1;
        scores.get(i).setScore(scores.get(i).getScore() + 1);
    }
}
