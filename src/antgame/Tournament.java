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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ADigenis
 */
public class Tournament extends JFrame {

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
        JTextField world;
        JTextField scoreField;
        JPanel outerPanel;
        JPanel innerPanel;

        innerPanel = new JPanel();
        outerPanel = new JPanel();
        scoreField = new JTextField();
        world = new JTextField();

        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        innerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Ant-World"));
        innerPanel.add(world);
        innerPanel.setPreferredSize(new Dimension(400, 0));
        outerPanel.add(innerPanel);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        innerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Score"));
        innerPanel.add(scoreField);
        outerPanel.add(innerPanel);

        return outerPanel;
    }
}
