package antgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains the main running of the game containing the world and the
 * ants, controlling the effects of a round and finding a winner
 * 
 * @author Team 13
 */
public class PlayGame {

    //two arrays to store the black and red ants.
    private ArrayList<Ant> redAnts = new ArrayList<>();
    private ArrayList<Ant> blackAnts = new ArrayList<>();
    
    //the map/world the game will play on
    private World gameWorld;
    
    //each teams antbrains stored in a List of Lists
    public List<List<String>> redAntBrain = new ArrayList<>();
    public List<List<String>> blackAntBrain = new ArrayList<>();
    
    //score variables and round counter
    private int redScore;
    private int blackScore;
    private int roundCounter = 0;

    
    /**
     * Constructor to set-up the new game inputting the map and both teams brains.
     * @param antBrain1 the red teams antBrain
     * @param antBrain2 the black teams antBrain
     * @param map the world object to be played on
    */
    public PlayGame(List<List<String>> antBrain1, List<List<String>> antBrain2, World map) {

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

    /**
     * @param inputAnt the ant object whose turn it is in the game
     * @throws Exception
    */
    private void step(Ant inputAnt) throws Exception {
        Ant currentAnt = inputAnt;
        int currentAntX = currentAnt.getAntX();
        int currentAntY = currentAnt.getAntY();
        List<List<String>> brain = new ArrayList<List<String>>();
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
                    currPos[0] = currentAntX;
                    currPos[1] = currentAntY;
                    int[] pdir = new int[2];
                    switch (brain.get(currentAnt.state).get(1)) {
                        case "here":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.HERE);
                            break;
                        case "ahead":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.AHEAD);
                            break;
                        case "leftahead":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.LEFTAHEAD);
                            break;
                        case "rightahead":
                            pdir = gameWorld.sensedCell(currPos, currentAnt.direction, senseDir.RIGHTAHEAD);
                            break;
                    }
                    if (cellMatches(pdir, condition.valueOf(brain.get(currentAnt.state).get(4).toUpperCase()), 0)) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(3)));
                    }
                    break;

                case "mark":
                    gameWorld.placeMarker(currentAnt, Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    break;

                case "unmark":
                    gameWorld.clearMarker(currentAnt);
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    break;

                case "pickup":
                    if (gameWorld.getFoodAt(currentAnt.positionX, currentAnt.positionY) == 0) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else if (!currentAnt.hasFood) {
                        gameWorld.setFoodAt(currentAnt.positionX, currentAnt.positionY, -1);
                        currentAnt.setHasFood(true);
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    }
                    break;

                case "drop":
                    if (currentAnt.hasFood) {
                        if (currentAnt.colour == 0 && gameWorld.getCellData(currentAnt.getAntX(), currentAnt.getAntY()).equals("+")) {
                            redScore++;
                        } else if (currentAnt.colour == 1 && gameWorld.getCellData(currentAnt.getAntX(), currentAnt.getAntY()).equals("-")) {
                            blackScore++;
                        } else {
                            gameWorld.setFoodAt(currentAnt.positionX, currentAnt.positionY, 1);
                        }
                        currentAnt.setHasFood(false);
                    }
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    break;

                case "turn":
                    // Gets left or right from the brain and turns accordingly.
                    currentAnt.setDirection(brain.get(currentAnt.state).get(1));
                    currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    break;

                case "move":
                    int[] newPos;
                    newPos = gameWorld.adjacentCell(currentAnt.positionX, currentAnt.positionY, currentAnt.direction);
                    if (gameWorld.getCellData(newPos[0], newPos[1]).equals("#")) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else if (isAntAt(newPos[0], newPos[1])) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        currentAnt.setPosition(newPos[0], newPos[1]);
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                        currentAnt.setResting(14);
                        int surroundedAntCounter = 0;
                        int surroundedRockCounter = 0;
                        // For all the directions around the ant:
                        for (int i = 0; i < 6; i++) {
                            // Check if it is rocky.
                            if (gameWorld.adjacentCell(currentAnt.getAntX(), currentAnt.getAntY(), i).equals("#")) {
                                surroundedRockCounter++;
                            } else if (currentAnt.colour == 1) {
                                Ant antCheck = redAnts.get(0);
                                Iterator itr = redAnts.iterator();
                                // If back, check if it is surrounded by red ants.
                                while (itr.hasNext()) {
                                    if (antCheck.positionX == gameWorld.adjacentCell(currentAnt.getAntX(), currentAnt.getAntY(), i)[0]
                                            && antCheck.positionY == gameWorld.adjacentCell(currentAnt.getAntX(), currentAnt.getAntY(), i)[1] 
                                            && !antCheck.isDead) {
                                        surroundedAntCounter++;
                                    }
                                    antCheck = (Ant) itr.next();
                                }
                            } else {
                                Ant antCheck = blackAnts.get(0);
                                Iterator itr = blackAnts.iterator();
                                // if red, check if it is surrounded by black ants. 
                                while (itr.hasNext()) {
                                    if (antCheck.positionX == gameWorld.adjacentCell(currentAnt.getAntX(), currentAnt.getAntY(), i)[0]
                                            && antCheck.positionY == gameWorld.adjacentCell(currentAnt.getAntX(), currentAnt.getAntY(), i)[1]
                                            && !antCheck.isDead) {
                                        surroundedAntCounter++;
                                    }
                                    antCheck = (Ant) itr.next();
                                }
                            }
                        }
                        
                        // Ant is surrounded by ants and walls, kill it! (making sure the ant is at least outnumbered.
                        if (surroundedAntCounter + surroundedRockCounter > 4 && surroundedAntCounter > 2){
                            currentAnt.setIsDead(true);
                        }
                        
                    }
                    break;

                case "flip":
                    RandomNumber rand = new RandomNumber(100);
                    int randomNumber = rand.randomInt(Integer.parseInt(brain.get(currentAnt.state).get(1)));
                    if (randomNumber == 0) {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(2)));
                    } else {
                        currentAnt.setState(Integer.parseInt(brain.get(currentAnt.state).get(3)));
                    }
                    break;
            }

        }
    }

    /**
     * @param x an integer representing the x coordinate of a board position
     * @param y an integer representing the y coordinate of a board position
     * @return a boolean true if ant at position given, false otherwise
    */
    private boolean isAntAt(int x, int y) {
        Ant antCheck = redAnts.get(0);
        Iterator itr = redAnts.iterator();
        while (itr.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return true;
            }
            antCheck = (Ant) itr.next();
        }

        antCheck = blackAnts.get(0);
        Iterator itrB = blackAnts.iterator();
        while (itrB.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return true;
            }
            antCheck = (Ant) itrB.next();
        }
        return false;
    }

    /**
     * @param x an integer representing the x coordinate of a board position
     * @param y an integer representing the y coordinate of a board position
     * @return the ant object at the position given
     * @throws Exception if no ant is at the position
    */
    private Ant antAt(int x, int y) throws Exception {
        Ant antCheck = redAnts.get(0);
        Iterator itr = redAnts.iterator();
        while (itr.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return antCheck;
            }
            antCheck = (Ant) itr.next();
        }

        antCheck = blackAnts.get(0);
        Iterator itrB = blackAnts.iterator();
        while (itrB.hasNext()) {
            if (antCheck.positionX == x && antCheck.positionY == y) {
                return antCheck;
            }
            antCheck = (Ant) itr.next();
        }
        throw new Exception("No ant in position given");
    }

    //returns int value of the other colour
    /**
     * @param colour an integer representing a colour
     * @return an integer representing the colour opposite to the parameter given
    */
    public int otherColour(int colour) {
        return Math.abs(colour - 1);
    }

    public enum condition {

        FRIEND, FOE, FRIENDWITHFOOD, FOEWITHFOOD, FOOD, ROCK, MARKER, FOEMARKER, HOME, FOEHOME
    }

    /**
     * @param p an array containing the coordinates of a cell in the world
     * @param cond a enum representing a certain condition
     * @param colour and integer representing a team colour
     * @return a boolean true if condition is true in given cell, false otherwise
     * @throws Exception
    */
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
                return (gameWorld.getFoodAt(p[0],p[1]) > 0);
            case ROCK:
                return (gameWorld.rocky(p));
            case HOME:
                return gameWorld.anthillAt(p, colour);
            case FOEHOME:
                return gameWorld.anthillAt(p, otherColour(colour));
        }
        return false;
    }

    /**
     * Runs the main frame of the game iterating through the rounds
     * @return an integer value used to determine the winner of the game
    */
    public int runGame() {

        // int to keep track of whos turn it is.
        boolean whichAntsTurn = true;

        // Initialise scores
        redScore = 0;
        blackScore = 0;

        // outer loop for rounds.
        while (roundCounter <= 300000) {
            int redAntIndexCounter = 0;
            int blackAntIndexCounter = 0;
            for (int i = 0; i < redAnts.size() + blackAnts.size(); i++) {
                // Red ants Turn
                if (whichAntsTurn) {   // RED Turn
                    // If the ant is alive
                    if (redAnts.get(redAntIndexCounter).isDead == false) {
                        try {
                            // *** Call do move shizzle method.
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
            roundCounter++;
        }
        
        System.out.println("RedScore: " + redScore);
        System.out.println("BlackScore: " + blackScore);

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
