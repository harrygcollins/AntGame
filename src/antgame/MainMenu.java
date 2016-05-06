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
 * Constructs the UI for the main menu of the game to choose between tournament
 * and single match
 *
 * @author Team 13
 */
public class MainMenu extends JFrame {

    /**
     * Constructor of the single match. Builds the UI for main menu
     */
    public MainMenu() {
        super("Ant-Game");
        buildUI();
    }

    /**
     * Creates the UI for the main menu
     */
    private void buildUI() {
        Container c;
        JLabel instruction;
        JButton singleMatch, tournament;

        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
        instruction = new JLabel("ANT-GAME");
        singleMatch = new JButton("Single Match");
        tournament = new JButton("Tournament");

        //adds rigid area to the frame
        c.add(Box.createRigidArea(new Dimension(0, 80)));
        c.add(instruction);

        instruction.setAlignmentX(CENTER_ALIGNMENT);
        instruction.setFont(new Font("Ravie", Font.PLAIN, 80));

        //adds rigid area to the frame
        c.add(Box.createRigidArea(new Dimension(0, 200)));
        c.add(singleMatch);

        // change format of the button
        singleMatch.setAlignmentX(CENTER_ALIGNMENT);
        singleMatch.setBorderPainted(false);
        singleMatch.setFocusPainted(false);
        singleMatch.setContentAreaFilled(false);
        singleMatch.setFont(new Font("Times New Roman", Font.PLAIN, 60));

        /**
         * creates effect when hovering over the button
         */
        singleMatch.addMouseListener(new MouseAdapter() {
            Font f = singleMatch.getFont();

            /**
             * makes the text of the button bold when mouse enters the area of
             * the button
             *
             * @param me the event that mouse entered button area
             */
            @Override
            public void mouseEntered(MouseEvent me) {
                singleMatch.setFont(new Font("Times New Roman", Font.BOLD, 60));
            }

            /**
             * sets the font of the button as it was before entering the area
             * button
             *
             * @param me the event that mouse left button area
             */
            @Override
            public void mouseExited(MouseEvent me) {
                singleMatch.setFont(f);
            }
        });

        singleMatch.addActionListener(new ActionListener() {
            /**
             * handles the event when the button is clicked. Close the main menu
             * frame and creates a single match frame
             *
             * @param e the event the table has been clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SingleMatch s = new SingleMatch();

            }
        });

        //adds rigid area to the frame
        c.add(Box.createRigidArea(new Dimension(0, 100)));
        c.add(tournament);

        // change format of the button
        tournament.setAlignmentX(CENTER_ALIGNMENT);
        tournament.setBorderPainted(false);
        tournament.setFocusPainted(false);
        tournament.setContentAreaFilled(false);
        tournament.setFont(new Font("Times New Roman", Font.PLAIN, 60));

        /**
         * creates effect when hovering over the button
         */
        tournament.addMouseListener(new MouseAdapter() {
            Font f = tournament.getFont();

            /**
             * makes the text of the button bold when mouse enters the area of
             * the button
             *
             * @param me the event that mouse entered button area
             */
            @Override
            public void mouseEntered(MouseEvent me) {
                tournament.setFont(new Font("Times New Roman", Font.BOLD, 60));
            }

            /**
             * sets the font of the button as it was before entering the area
             * button
             *
             * @param me the event that mouse left button area
             */
            @Override
            public void mouseExited(MouseEvent me) {
                tournament.setFont(f);
            }
        });
        tournament.addActionListener(new ActionListener() {
            /**
             * handles the event when the button is clicked. Close the main menu
             * frame and creates a tournament frame
             *
             * @param e the event the table has been clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Tournament t = new Tournament();

            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
