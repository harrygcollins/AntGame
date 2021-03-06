package antgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

/**
 * This class allows the ant brain(s) submitted at run time to be parsed and
 * checked that they are syntactically correct
 *
 * @author Team 13
 */
public class AntBrainParser {

    /**
     * Array List variable to hold the ant brain.
     */
    public List<List<String>> brainList = new ArrayList<>();

    /**
     * Boolean to keep track if the brain is valid or not.
     */
    public boolean validBrain = false;

    /**
     * Constructor for the ant brain parser.
     *
     * @param f The input file ready to be parsed.
     * @return the Array list ( List<List<String>> ) containing the ant brain.
     */
    public List<List<String>> AntBrainParser(File f) {
        Scanner s = null;
        // Import a file to be parsed. 
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntBrainParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Scan the first line of the file.

        // For each line in the file
        while (s.hasNextLine()) {
            String line = s.nextLine();
            //Create a new scanner and array list for this row. 
            Scanner rowScanner = new Scanner(line);
            List<String> tempList = new ArrayList<>();

            // Scan the words in this row and add them to the arraylist. 
            while (rowScanner.hasNext()) {
                String temp = rowScanner.next().toLowerCase();
                // Ignore any comments. 
                if (!(temp.contains(";"))) {
                    tempList.add(temp);
                } else {
                    break;
                }
            }
            rowScanner.close();
            // Add the inner arraylist to the outer. 
            brainList.add(tempList);
        }
        // Close the scanner. 
        s.close();

        List<List<String>> brainListValid = new ArrayList<>();
        brainListValid = brainList;

        return brainList;
    }

    /**
     * The parser to check if the brain is valid.
     *
     * @param antBrain Input the Array list created from the file.
     * @return True if the brain is valid, false otherwise.
     * @throws SyntaxException
     */
    public Boolean IsValidBrain(List<List<String>> antBrain) throws SyntaxException {
        List<List<String>> token = antBrain;

        while (!token.isEmpty()) {
            // Instruction Sense Test Case
            if (token.get(0).get(0).equals("sense")) {
                if (token.get(0).get(1).equals("here") || token.get(0).get(1).equals("ahead") || token.get(0).get(1).equals("leftahead") || token.get(0).get(1).equals("rightahead")) {
                    if ((Integer.parseInt(token.get(0).get(2)) >= 0 && Integer.parseInt(token.get(0).get(2)) <= 9999)) {
                        if ((Integer.parseInt(token.get(0).get(3)) >= 0 && Integer.parseInt(token.get(0).get(3)) <= 9999)) {
                            if ((token.get(0).get(4).equals("friend") || token.get(0).get(4).equals("foe") || token.get(0).get(4).equals("friendwithfood") || token.get(0).get(4).equals("foewithfood") || token.get(0).get(4).equals("food") || token.get(0).get(4).equals("rock") || token.get(0).get(4).equals("foemarker") || token.get(0).get(4).equals("home") || token.get(0).get(4).equals("foehome"))) {
                                token.remove(0);
                            } else if (token.get(0).get(4).equals("marker")) {
                                if (Integer.parseInt(token.get(0).get(5)) >= 0 && Integer.parseInt(token.get(0).get(5)) <= 5) {
                                    token.remove(0);
                                }
                            }
                        }
                    }
                }
                // Instruction mark and unmark
            } else if (token.get(0).get(0).equals("mark") || token.get(0).get(0).equals("unmark")) {
                if (Integer.parseInt(token.get(0).get(1)) >= 0 && Integer.parseInt(token.get(0).get(1)) <= 5) {
                    if (Integer.parseInt(token.get(0).get(2)) >= 0 && Integer.parseInt(token.get(0).get(2)) <= 9999) {
                        token.remove(0);
                    }
                }
                // Instruction pickup and move  
            } else if (token.get(0).get(0).equals("pickup") || token.get(0).get(0).equals("move")) {
                if ((Integer.parseInt(token.get(0).get(1)) >= 0 && Integer.parseInt(token.get(0).get(1)) <= 9999)) {
                    if ((Integer.parseInt(token.get(0).get(2)) >= 0 && Integer.parseInt(token.get(0).get(2)) <= 9999)) {
                        token.remove(0);
                    }
                }
            } // Instruction drop
            else if (token.get(0).get(0).equals("drop")) {
                if ((Integer.parseInt(token.get(0).get(1)) >= 0 && Integer.parseInt(token.get(0).get(1)) <= 9999)) {
                    token.remove(0);
                }
            } // Instruction Turn
            else if (token.get(0).get(0).equals("turn")) {
                if (token.get(0).get(1).equals("left") || token.get(0).get(1).equals("right")) {
                    if ((Integer.parseInt(token.get(0).get(2)) >= 0 && Integer.parseInt(token.get(0).get(2)) <= 9999)) {
                        token.remove(0);
                    }
                }
            } // Instruction Flip
            else if (token.get(0).get(0).equals("flip")) {
                if (Integer.parseInt(token.get(0).get(1)) >= 1) {
                    if ((Integer.parseInt(token.get(0).get(2)) >= 0 && Integer.parseInt(token.get(0).get(2)) <= 9999)) {
                        if ((Integer.parseInt(token.get(0).get(3)) >= 0 && Integer.parseInt(token.get(0).get(3)) <= 9999)) {
                            token.remove(0);
                        }
                    }
                }
            } // The instruction has not been recognised. 
            else {
                return false;
            }
        }
        // Token list is empy, meaning all tests have passes. Valid brain. 
        return true;
    }

}
