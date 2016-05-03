/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harry
 */
public class PlayGame {

    // Two arrays to store the black and red ants.
    private ArrayList<Ant> redAnts = new ArrayList<>();
    private ArrayList<Ant> blackAnts = new ArrayList<>();
    private World gameWorld;
    public List<List<String>> redAntBrain = new ArrayList<>();
    public List<List<String>> blackAntBrain = new ArrayList<>();

    private int redScore;
    private int blackScore;

    private int roundCounter = 0;

//<<<<<<< HEAD
//    public PlayGame(File redFile, File blackFile, World map) {
//=======
    public PlayGame(List<List<String>> antBrain1, List<List<String>> antBrain2, World map) {
//>>>>>>> origin/master

        // Assign all the arugments to local variables.
        AntBrainParser parser = new AntBrainParser();

        System.out.println("Making red ant brain");
        //redAntBrain = parser.AntBrainParser(redFile);
        redAntBrain = antBrain1;
        System.out.println("Red ant brain size: " + redAntBrain.size());

        //blackAntBrain = parser.AntBrainParser(blackFile);
        blackAntBrain = antBrain2;
        System.out.println("Black ant brain size: " + blackAntBrain.size());
        gameWorld = map;

        //Initialise the scores
        redScore = 0;
        blackScore = 0;

        //Initialise the ant ID counters.
        int redIdCounter = 0;
        int blackIdCounter = 0;

        // Add ants into the world based on the size of the ant hills.
        for (int i = 0; i < gameWorld.getMapHeight(); i++) {
            for (int j = 0; j < gameWorld.getMapWidth(); j++) {
                if (null != gameWorld.getCellData(i, j)) {
                    switch (gameWorld.getCellData(i, j)) {
                        case "+":
                            redAnts.add(new Ant(0, j, i, redIdCounter));
                            System.out.println("Red ant " + redIdCounter + " added!");
                            redIdCounter++;
                            break;
                        case "-":
                            blackAnts.add(new Ant(1, j, i, blackIdCounter));
                            System.out.println("Black ant " + blackIdCounter + "  added!");
                            blackIdCounter++;
                            break;
                    }
                }
            }
        }
    }

    private void step(Ant inputAnt) throws Exception {
        Ant currentAnt = inputAnt;
        int currentAntX = currentAnt.positionX;
        int currentAntY = currentAnt.positionY;
        List<List<String>> brain = new ArrayList<>();
        if (inputAnt.colour == 0) {
            brain = redAntBrain;
        } else {
            brain = blackAntBrain;
        }

        if (currentAnt.getResting() > 0) {
            currentAnt.setResting(currentAnt.getResting() - 1);
        } else {
            //if (currentAnt.colour == 0) { // Extract this and set variable brain to the colour of the ant brain. 
            switch (brain.get(currentAnt.state).get(0)) {
                case "sense":
                    int[] currPos = new int[2];
                    currPos[0] = currentAnt.getAntX();
                    currPos[1] = currentAnt.getAntY();
                    int[] pdir = new int[2];
                    System.out.println("Current State: " + brain.get(currentAnt.state).get(0));
                    System.out.println("Next State: " + brain.get(currentAnt.state).get(1));
                    switch (brain.get(currentAnt.state).get(1)) {
                        case "here":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.HERE);
                        case "ahead":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.AHEAD);
                        case "leftahead":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.LEFTAHEAD);
                        case "rightahead":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.RIGHTAHEAD);
                    }
                    if (cellMatches(pdir, condition.valueOf(brain.get(currentAnt.state).get(4).toUpperCase()), 0)) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(3)));
                    }

                case "mark":
                    gameWorld.placeMarker(currentAnt, Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));

                case "unmark":
                    gameWorld.clearMarker(currentAnt);
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));

                case "pickup":
                    if (!currentAnt.hasFood || gameWorld.getFoodAt(currentAnt.positionX, currentAnt.positionY) == 0) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        gameWorld.setFoodAt(currentAnt.positionX, currentAnt.positionY, -1);
                        currentAnt.setHasFood(true);
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    }

                case "drop":
                    if (currentAnt.hasFood) {
                        if (currentAnt.colour == 0 && "+".equals(gameWorld.getCellData(currentAnt.getAntX(), currentAnt.getAntY()))) {
                            redScore++;
                        } else if (currentAnt.colour == 1 && "-".equals(gameWorld.getCellData(currentAnt.getAntX(), currentAnt.getAntY()))) {
                            blackScore++;
                        } else {
                            gameWorld.setFoodAt(currentAnt.positionX, currentAnt.positionY, 1);
                        }
                        currentAnt.setHasFood(false);
                    }
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(1)));

                case "turn":
                    // Gets left or right from the brain and turns accordingly.
                    currentAnt.setDirection(brain.get(currentAnt.state).get(1));
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));

                case "move":
                    int[] newPos;
                    newPos = gameWorld.adjacentCell(currentAnt.positionX, currentAnt.positionY, currentAnt.direction);
                    if ("#".equals(gameWorld.getCellData(newPos[0], newPos[1]))) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else if (isAntAt(currentAnt.positionX, currentAnt.positionY)) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        currentAnt.setPosition(newPos[0], newPos[1]);
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                        currentAnt.setResting(14);
                        // *** Check if the ant is surrounded *** ///
                    }

                case "flip":
                    RandomNumber rand = new RandomNumber(100);
                    int randomNumber = rand.randomInt(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    if (randomNumber == 0) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(3)));
                    }
            }

        }
    }

    //returns true if ant at position given, false otherwise
    private boolean isAntAt(int x, int y) {
        Ant antCheck = redAnts.get(0);
        Iterator itr = redAnts.iterator();
        while (itr.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return true;
            }
            antCheck = (Ant)itr.next();
        }

        
        antCheck = blackAnts.get(0);
        Iterator itrB = blackAnts.iterator();
        while (itrB.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return true;
            }
            antCheck = (Ant)itrB.next();
        }
        return false;
    }

    //returns the ant at the position given
    private Ant antAt(int x, int y) throws Exception {
        Ant antCheck = redAnts.get(0);
        Iterator itr = redAnts.iterator();
        while (itr.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return antCheck;
            }
            antCheck = (Ant)itr.next();
        }

        antCheck = blackAnts.get(0);
        Iterator itrB = blackAnts.iterator();
        while (itrB.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return antCheck;
            }
            antCheck = (Ant)itr.next();
        }
        throw new Exception("No ant in position given");
    }

    //sets the ant at position given
    private void setAntAt(int[] pos, Ant a) {
        a.setPosition(pos[0], pos[1]);
    }

    //returns int value of the other colour
    public int otherColour(int colour) {
        return Math.abs(colour - 1);
    }

    public enum condition {

        FRIEND, FOE, FRIENDWITHFOOD, FOEWITHFOOD, FOOD, ROCK, MARKER, FOEMARKER, HOME, FOEHOME
    }

    public boolean cellMatches(int[] p, condition cond, int colour) throws Exception {
        switch (cond) {
            case FRIEND:
                return (isAntAt(p[0], p[1]) && antAt(p[0], p[1]).getColour() == colour);
            case FOE:
                return (isAntAt(p[0], p[1]) && antAt(p[0], p[1]).getColour() != colour);
            case FRIENDWITHFOOD:
                return (isAntAt(p[0], p[1]) && antAt(p[0], p[1]).getColour() == colour && antAt(p[0], p[1]).getHasFood());
            case FOEWITHFOOD:
                return (isAntAt(p[0], p[1]) && antAt(p[0], p[1]).getColour() != colour && antAt(p[0], p[1]).getHasFood());
            case FOOD:
                return (gameWorld.foodAt(p) > 0);
            case ROCK:
                return (gameWorld.rocky(p));
            //case MARKER(i):
            //check_marker_at(p,c,i)
            //case FOEMARKER:
            //check_any_marker_at(p, other_colour(c))
            case HOME:
                return gameWorld.anthillAt(p, colour);
            case FOEHOME:
                return gameWorld.anthillAt(p, otherColour(colour));
        }
        return false;
    }

    public int runGame() {

        // int to keep track of whos turn it is.
        boolean whichAntsTurn = true;

        // Initialise scores
        redScore = 0;
        blackScore = 0;

        System.out.println("Length of Red Ant Array: " + redAnts.size());
        System.out.println("Length of Black Ant Array: " + blackAnts.size());

        // outer loop for rounds.
        while (roundCounter <= 30) {
            int redAntIndexCounter = 0;
            int blackAntIndexCounter = 0;
            for (int i = 0; i < redAnts.size() + blackAnts.size(); i++) {
                System.out.println("i : " + i);
                // Red ants Turn
                if (whichAntsTurn) {   // RED Turn
                    // If the ant is alive
                    if (redAnts.get(redAntIndexCounter).isDead == false) {
                        try {
                            // *** Call do move shizzle method.
                            System.out.println(redAnts.get(redAntIndexCounter));
                            step(redAnts.get(redAntIndexCounter));
                            redAntIndexCounter++;
                            // Make it black ants turn.
                            whichAntsTurn = false;
                        } catch (Exception ex) {

                            System.out.println("Exception: Red ant " + redAntIndexCounter + " NotFound");
                        }

                    } // Black ants Turn
                } else {                  // BLACK Turn
                    // If the ant is alive
                    if (blackAnts.get(blackAntIndexCounter).isDead == false) {
                        try {
                            // *** Call do move shizzle method.
                            step(blackAnts.get(blackAntIndexCounter));
                            blackAntIndexCounter++;
                            // Make it red ants turn.
                            whichAntsTurn = true;
                        } catch (Exception ex) {
                            System.out.println("Exception: Black ant " + blackAntIndexCounter + " NotFound");
                        }
                    }

                }
            }
        }

        //Return the game score  
        if (redScore > blackScore) {
            // Red team won.
            return 0;
        } else if (blackScore > redScore) {
            // Black team won.
            return 2;
        } else {
            // Draw.
            return 1;
        }

    }
}
