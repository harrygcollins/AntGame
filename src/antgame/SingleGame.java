/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ADigenis
 */
public class SingleGame extends JFrame {

    public SingleGame(){
        buildUI();
    }
    private void buildUI() {
        Container c;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        
        c.add(createUsersInfoAndStart(), BorderLayout.NORTH);
        c.add(createWorldField(), BorderLayout.CENTER);
        c.setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private JPanel createUsersInfoAndStart() {
        JPanel outerPanel;
        JPanel innerPanel;
        JLabel teamNo1;
        JLabel teamNo2;
        JButton upload1;
        JButton upload2;
        JButton start;

        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        outerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Player info"));

        teamNo1 = new JLabel("Team 1");
        teamNo2 = new JLabel("Team 2");
        upload1 = new JButton("Upload Ant-Brain");
        upload2 = new JButton("Upload Ant-Brain");
        start = new JButton("Start");
        
        outerPanel.add(Box.createRigidArea(new Dimension(20,0)));
        innerPanel.add(teamNo1);
        innerPanel.add(upload1);
        outerPanel.add(innerPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(20,0)));

        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        innerPanel.add(start);
        outerPanel.add(innerPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(20,0)));

        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        innerPanel.add(teamNo2);
        innerPanel.add(upload2);
        outerPanel.add(innerPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(20,0)));

        return outerPanel;
    }
    
    public JPanel createWorldField(){
        JTextField world;
        JPanel outerPanel;
        
        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        outerPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Ant-World"));
        world = new JTextField();
        
        outerPanel.add(world);
        
        return outerPanel;
    }  
}
