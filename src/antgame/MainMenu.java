/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author ADigenis
 */
public class MainMenu extends JFrame {

    public MainMenu() {
        super("Ant-Game");
        buildUI();
    }

    private void buildUI() {
        Container c;
        JLabel instruction;
        JButton singleMatch, tournament;

        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
        instruction = new JLabel("ANT-GAME");
        singleMatch = new JButton("Single Match");
        tournament = new JButton("Tournament");

        c.add(Box.createRigidArea(new Dimension(0, 80)));
        c.add(instruction);
        instruction.setAlignmentX(CENTER_ALIGNMENT);
        instruction.setFont(new Font("Ravie", Font.PLAIN, 80));
        c.add(Box.createRigidArea(new Dimension(0, 200)));
        c.add(singleMatch);
        singleMatch.setAlignmentX(CENTER_ALIGNMENT);
        singleMatch.setBorderPainted(false);
        singleMatch.setFocusPainted(false);
        singleMatch.setContentAreaFilled(false);
        singleMatch.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        singleMatch.addMouseListener(new MouseAdapter() {
            Font f = singleMatch.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                singleMatch.setFont(new Font("Times New Roman", Font.BOLD, 60));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                singleMatch.setFont(f);
            }
        });
        singleMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SingleMatch s = new SingleMatch();

            }
        });

        c.add(Box.createRigidArea(new Dimension(0, 100)));
        c.add(tournament);
        tournament.setAlignmentX(CENTER_ALIGNMENT);
        tournament.setBorderPainted(false);
        tournament.setFocusPainted(false);
        tournament.setContentAreaFilled(false);
        tournament.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        tournament.addMouseListener(new MouseAdapter() {
            Font f = tournament.getFont();

            @Override
            public void mouseEntered(MouseEvent me) {
                tournament.setFont(new Font("Times New Roman", Font.BOLD, 60));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                tournament.setFont(f);
            }
        });
        tournament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Tournament t = new Tournament();

            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
