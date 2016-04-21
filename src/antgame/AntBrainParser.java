/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Harry
 */
public class AntBrainParser {

    public List<String> token = new ArrayList();
    public boolean validBrain = false;

    public AntBrainParser() {
        Scanner s = null;
        try {
            s = new Scanner(new File("ANTBRAIN"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntBrainParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next().toLowerCase());
        }
        s.close();
        
        this.token = list;
    }

    public Boolean IsValidBrain(List<String> antBrain) throws SyntaxException {
        token = antBrain;

//        for (int i = 0; i < token.size(); i++) {
//            System.out.println(token.get(i));
//        }

        while (token.isEmpty() == false) {
            System.out.println("Got into the test Case");
            // Instruction Sense Test Case
            if (token.get(0).equals("sense")) {
                if (token.get(1).equals("here") || token.get(1).equals("ahead") || token.get(1).equals("leftahead") || token.get(1).equals("rightahead")) {
                    if ((Integer.parseInt(token.get(2)) >= 0 && Integer.parseInt(token.get(2)) <= 9999)) {
                        if ((Integer.parseInt(token.get(3)) >= 0 && Integer.parseInt(token.get(3)) <= 9999)) {
                            if ((token.get(4).equals("friend") || token.get(4).equals("foe") || token.get(4).equals("friendwithfood") || token.get(4).equals("foewithfood") || token.get(4).equals("food") || token.get(4).equals("rock") || token.get(4).equals("foemarker") || token.get(4).equals("home") || token.get(4).equals("foehome"))) {
                                token.remove(4);
                                token.remove(3);
                                token.remove(2);
                                token.remove(1);
                                token.remove(0);
                            } else if (token.get(4).equals("marker")) {
                                if (Integer.parseInt(token.get(5)) >= 0 && Integer.parseInt(token.get(5)) <= 5) {
                                    token.remove(5);
                                    token.remove(4);
                                    token.remove(3);
                                    token.remove(2);
                                    token.remove(1);
                                    token.remove(0);
                                }
                            }
                        }
                    }
                }
                // Instruction mark and unmark
                System.out.println("Got into mark or unmark function");
            } else if (token.get(0).equals("mark") || token.get(0).equals("unmark")) {
                if (Integer.parseInt(token.get(1)) >= 0 && Integer.parseInt(token.get(1)) <= 5) {
                    if (Integer.parseInt(token.get(2)) >= 0 && Integer.parseInt(token.get(2)) <= 9999) {
                        token.remove(2);
                        token.remove(1);
                        token.remove(0);
                    }
                }
                // Instruction pickup and move  
                System.out.println("Got into pickup or move function");
            } else if (token.get(0).equals("pickup") || token.get(0).equals("move")) {
                if ((Integer.parseInt(token.get(1)) >= 0 && Integer.parseInt(token.get(1)) <= 9999)) {
                    if ((Integer.parseInt(token.get(2)) >= 0 && Integer.parseInt(token.get(2)) <= 9999)) {
                        token.remove(2);
                        token.remove(1);
                        token.remove(0);
                    }
                }
            } // Instruction drop
            else if (token.get(0).equals("drop")) {
                System.out.println("Got into drop function");
                if ((Integer.parseInt(token.get(1)) >= 0 && Integer.parseInt(token.get(1)) <= 9999)) {
                    System.out.println("Recognised the 5");
                    token.remove(1);
                    System.out.println("Deleted Token 1");
                    token.remove(0);
                    System.out.println("Is the list empty : " + token.isEmpty());
                }
            } // Instruction Turn
            else if (token.get(0).equals("turn")) {
                System.out.println("Got into turn function");
                if (token.get(1).equals("left") || token.get(1).equals("right")) {
                    if ((Integer.parseInt(token.get(2)) >= 0 && Integer.parseInt(token.get(2)) <= 9999)) {
                        token.remove(2);
                        token.remove(1);
                        token.remove(0);
                    }
                }
            } // Instruction Flip
            else if (token.get(0).equals("flip")) {
                System.out.println("Got into flip function");
                if (Integer.parseInt(token.get(1)) >= 1) {
                    if ((Integer.parseInt(token.get(2)) >= 0 && Integer.parseInt(token.get(2)) <= 9999)) {
                        if ((Integer.parseInt(token.get(3)) >= 0 && Integer.parseInt(token.get(3)) <= 9999)) {
                            token.remove(3);
                            token.remove(2);
                            token.remove(1);
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
