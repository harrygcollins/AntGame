/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

//import static java.accessibility.util.AWTEventMonitor.addActionListener;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ADigenis
 */
public class SingleMatch extends JFrame {

    private List<List<String>> antBrainTeam1;
    private List<List<String>> antBrainTeam2;
    private World newWorld = new World(50, 50);
    private PlayGame newGame;
    private int winner;

    public SingleMatch() {
        super("Single Match");
        buildUI();
    }

    private void buildUI() {
        Container c;
        c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(createUsersInfoAndStart(), BorderLayout.WEST);

        c.add(createWorldField(), BorderLayout.CENTER);
        c.setPreferredSize(new Dimension(800, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private JPanel createUsersInfoAndStart() {
        JPanel outerPanel, userInfo;
        JLabel team1, team2;
        JButton uploadTeam1, uploadTeam2, startBtn;

        userInfo = new JPanel();
        outerPanel = new JPanel();
        team1 = new JLabel("Team 1");
        team1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        team2 = new JLabel("Team 2");
        team2.setFont(new Font("Times New Roman", Font.PLAIN, 18));

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
            @Override
            public void actionPerformed(ActionEvent e) {
                if (antBrainTeam1 != null && antBrainTeam2 != null && newWorld != null) {
                    newGame = new PlayGame(antBrainTeam1, antBrainTeam2, newWorld);
                    winner = newGame.runGame();
                    if (winner == 0) {
                        JOptionPane.showMessageDialog(rootPane, "The winner is Team 1!");
                    } else if (winner == 1) {
                        JOptionPane.showMessageDialog(rootPane, "The winner is Team 2!");
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
        uploadTeam1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        uploadTeam1.addMouseListener(new MouseAdapter() {
            Font f = uploadTeam1.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                uploadTeam1.setFont(new Font("Times New Roman", Font.BOLD, 16));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                uploadTeam1.setFont(f);
            }
        });
        uploadTeam1.addActionListener(new ActionListener() {
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
        uploadTeam2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        uploadTeam2.addMouseListener(new MouseAdapter() {
            Font f = uploadTeam2.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                uploadTeam2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                uploadTeam2.setFont(f);
            }
        });
        uploadTeam2.addActionListener(new ActionListener() {
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
        outerPanel.setPreferredSize(new Dimension(150, 0));

        return outerPanel;
    }

    public JPanel createWorldField() {
        JTextArea world;
        JPanel outerPanel;

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        outerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Ant-World"));
        world = new JTextArea(newWorld.testWorld());

        outerPanel.add(world);

        return outerPanel;
    }
}
