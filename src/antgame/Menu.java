/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author ADigenis
 */
public class Menu extends JFrame {

    JButton singleMatch;
    JButton tournament;

    public Menu() {
        buildUI();
    }

    private void buildUI() {
        Container c;
        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

        c.add(createTitle());
        c.add(createSelections());
//        c.add(createButton(), BorderLayout.SOUTH);
//        c.setPreferredSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private JPanel createTitle() {
        JPanel outerPanel;
        JLabel instruction;

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        instruction = new JLabel("ANT-GAME");
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
        outerPanel.add(instruction);

        return outerPanel;
    }

    private JPanel createSelections() {
        JPanel outerPanel;

        outerPanel = new JPanel(new GridLayout(0, 1));
        singleMatch = new JButton("Single Match");
        tournament = new JButton("Tournament");
        singleMatch.setPreferredSize(new Dimension(1,1));
        singleMatch.setMaximumSize(new Dimension(3,3));
        outerPanel.add(singleMatch);
        outerPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        outerPanel.add(tournament);

        return outerPanel;
    }

//    private JPanel createButton(){
//        JPanel outerPanel;
//        JButton continueButton;
//        
//        outerPanel = new JPanel(new BorderLayout());
//        continueButton = new JButton("Continue");
//        
//        outerPanel.add(continueButton, BorderLayout.LINE_END);
//        
//        return outerPanel;        
//    }
}
