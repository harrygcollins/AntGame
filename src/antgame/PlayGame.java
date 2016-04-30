/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harry
 */
public class PlayGame {

    // Two arrays to store the black and red ants.
    private ArrayList<Ant> redAnts = new ArrayList<>();
    private ArrayList<Ant> blackAnts = new ArrayList<>();
    private World gameWorld;
    private List<String> redAntBrain;
    private List<String> blackAntBrain;

    private int redScore;
    private int blackScore;

    private int roundCounter = 0;

    PlayGame(List<String> antBrain1, List<String> antBrain2, World map) {

        // Assign all the arugments to local variables.
        redAntBrain = antBrain1;
        blackAntBrain = antBrain2;
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
                            redIdCounter++;
                            break;
                        case "-":
                            blackAnts.add(new Ant(1, j, i, blackIdCounter));
                            blackIdCounter++;
                            break;
                    }
                }
            }
        }
    }

    private void step(Ant inputAnt) {
        Ant currentAnt = inputAnt;
        int currentAntX = currentAnt.positionX;
        int currentAntY = currentAnt.positionY;

        if (currentAnt.getResting() > 0) {
            currentAnt.setResting(currentAnt.getResting() - 1);
        } else {
            if (currentAnt.colour == 0) { // Extract this and set variable brain to the colour of the ant brain. 
                switch (redAntBrain.get(currentAnt.state)) {
                    case "sense":

                    case "mark":

                    case "unmark":

                    case "pickup":
                        if (!currentAnt.hasFood || gameWorld.getFoodAt(currentAnt.positionX, currentAnt.positionY) == 0) {
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 2)));
                        } else {
                            gameWorld.setFoodAt(currentAnt.positionX, currentAnt.positionY, -1);
                            currentAnt.setHasFood(true);
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 1)));
                        }

                    case "drop":
                        if (currentAnt.hasFood) {
                            gameWorld.setFoodAt(currentAnt.positionX, currentAnt.positionY, 1);
                            currentAnt.setHasFood(false);
                        }
                        currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 1)));

                    case "turn":
                        // Gets left or right from the brain and turns accordingly.
                        currentAnt.setDirection(redAntBrain.get(currentAnt.state + 1));
                        currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 2)));

                    case "move":
                        int[] newPos;
                        newPos = gameWorld.adjacentCell(currentAnt.positionX, currentAnt.positionY, currentAnt.direction);
                        if (gameWorld.getCellData(newPos[0], newPos[1]) == "#") {
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 2)));
                        } else if (isAntAt(currentAnt.positionX, currentAnt.positionY)) {
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 2)));       
                        } else {
                            currentAnt.setPosition(newPos[0], newPos[1]);
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 1)));   
                            currentAnt.setResting(14);
                            // *** Check if the ant is surrounded *** ///
                        }

                    case "flip":
                        RandomNumber rand = new RandomNumber(100);
                        int randomNumber = rand.randomInt(Integer.parseInt(redAntBrain.get(currentAnt.state + 1)));
                        if(randomNumber == 0){
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 2)));
                        } else {
                            currentAnt.setState(Integer.parseInt(redAntBrain.get(currentAnt.state + 3)));
                        }
                }
            }
        }
    }

    //returns true if ant at position given, false otherwise
    private boolean isAntAt(int x, int y) {
        Ant antCheck = redAnts.get(0);
        while (redAnts.iterator().hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return true;
            }
            antCheck = redAnts.iterator().next();
        }

        antCheck = blackAnts.get(0);
        while (blackAnts.iterator().hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return true;
            }
            antCheck = blackAnts.iterator().next();
        }
        return false;
    }
    
    //returns the ant at the position given
    private Ant antAt(int x, int y) throws Exception {
        Ant antCheck = redAnts.get(0);
        while (redAnts.iterator().hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return antCheck;
            }
            antCheck = redAnts.iterator().next();
        }

        antCheck = blackAnts.get(0);
        while (blackAnts.iterator().hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return antCheck;
            }
            antCheck = blackAnts.iterator().next();
        }
        throw new Exception("No ant in position given");
    }
    
    //sets the ant at position given
    private void setAntAt(int[] pos, Ant a) {
        a.setPosition(pos[0], pos[1]);
    }
    
    //returns int value of the other colour
    public int otherColour(int colour) {
        return Math.abs(colour-1);
    }
    
    public enum condition {

        FRIEND, FOE, FRIENDWITHFOOD, FOEWITHFOOD, FOOD, ROCK, MARKER, FOEMARKER, HOME, FOEHOME
    }

    public boolean cellMatches(int[] p, condition cond, int colour) throws Exception {
        switch (cond) {
            case FRIEND:
                return (isAntAt(p[0],p[1]) && antAt(p[0],p[1]).getColour() == colour);
            case FOE:
                return (isAntAt(p[0],p[1]) && antAt(p[0],p[1]).getColour() != colour);
            case FRIENDWITHFOOD:
                return (isAntAt(p[0],p[1]) && antAt(p[0],p[1]).getColour() == colour && antAt(p[0],p[1]).getHasFood());
            case FOEWITHFOOD:
                return (isAntAt(p[0],p[1]) && antAt(p[0],p[1]).getColour() != colour && antAt(p[0],p[1]).getHasFood());
            case FOOD:
                return (gameWorld.foodAt(p) > 0);
            case ROCK:
                return (gameWorld.rocky(p));
            //case MARKER(i):
                //check_marker_at(p,c,i)
            //case FOEMARKER:
                //check_any_marker_at(p, other_colour(c))
            case HOME:
                return gameWorld.anthillAt(p,colour);
            case FOEHOME:
                return gameWorld.anthillAt(p,otherColour(colour));
        }
        return false;
    }

    private int runGame() {

        // int to keep track of whos turn it is.
        int whichAntsTurn = 0;

        // outer loop for rounds.
        while (roundCounter <= 30) {
            int redAntIndexCounter = 0;
            int blackAntIndexCounter = 0;
            for (int i = 0; i < redAnts.size() + blackAnts.size(); i++) {
                // Red ants Turn
                if (whichAntsTurn == 0) {
                    // If the ant is alive
                    if (redAnts.get(redAntIndexCounter).isDead == false) {
                        // *** Call do move shizzle method.
                        step(redAnts.get(redAntIndexCounter));

                        // Make it black ants turn.
                        whichAntsTurn = 1;
                    } // Black ants Turn
                    else {
                        // If the ant is alive
                        if (blackAnts.get(blackAntIndexCounter).isDead == false) {
                            // ****** Call do move shizzle method.
                        }
                        // Make it red ants turn.
                        whichAntsTurn = 0;
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
