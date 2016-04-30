/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Harry
 */
public class World {

    private static World instance = null;
    private int mapWidth;
    private int mapHeight;
    private final String[][] world;

    // Constructor for the World.
    public World(int MapWidth, int MapHeight) {
        this.mapWidth = MapWidth;
        this.mapHeight = MapHeight;

        world = new String[mapWidth][mapHeight];

        addBorder();
        addAntHill();
        addFood();
        addRocks();

        testWorld();
    }

    // If you want to use the world, use the getInstance() method.
    // *** Needs to be finished to work ***
    public static World getInstance() {
        return instance;
    }

    // Returns The Height of the Map.
    public int getMapHeight() {
        return mapHeight;
    }

    // Returns the width of the map.
    public int getMapWidth() {
        return mapWidth;
    }

    public String getCellData(int x, int y) {
        return world[x][y];
    }

    public int getFoodAt(int x, int y) {
        if (isInteger(world[x][y])) {
            return Integer.parseInt(world[x][y]);
        } else {
            return -1;
        }
    }
    
    public void setFoodAt(int x, int y, int amount){
        if(isInteger(world[x][y])){
            world[x][y] = Integer.toString(Integer.parseInt(world[x][y]) + amount);
        } else if(world[x][y] == "."){
            world[x][y] = Integer.toString(amount);
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    // Adds a border to the world and fills the inside with .'s.
    private void addBorder() {
        for (int i = 0; i < mapHeight; i++) {

            for (int j = 0; j < getMapWidth(); j++) {
                if (i == 0 || i == mapHeight - 1 || j == mapWidth - 1 || j == 0) {
                    world[i][j] = "#";
                } else {
                    world[i][j] = ".";
                }
            }

        }
    }

    // Adds two randomly placed ant hills into the world. 
    private void addAntHill() {
        int antHillX = 7;
        int antHillY = 7;
        Random r = new Random();
        int antHillsNeeded = 2;
        int antHillsPlaced = 0;
        boolean spaceAvaliable;
        int Low = 0;
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (antHillsPlaced < antHillsNeeded) {
            spaceAvaliable = true;
            int spaceResultX = r.nextInt(HighX - Low) + Low;        // Get Random X Coordinate
            int spaceResultY = r.nextInt(HighY - Low) + Low;        // Get Random Y Coordinate

            // Check the antHill will be placed within the map and on a valid position (all .'s).
            if (spaceResultX > 0 && (spaceResultX + antHillX - 1) < HighX - 1 && spaceResultY > 0 && (spaceResultY + antHillY - 1) < HighY - 1 && spaceAvaliable) {
                for (int counterX = 0; counterX <= 4; counterX++) {
                    for (int counterY = 0; counterY <= 4; counterY++) {
                        spaceAvaliable = world[spaceResultX + counterX][spaceResultY + counterY].equals(".") && spaceAvaliable;
                    }
                }

                // If it is, place the antHill food. 
                if (spaceAvaliable) {
                    for (int counterX = 0; counterX < antHillX; counterX++) {
                        for (int counterY = 0; counterY < antHillY; counterY++) {
                            if (antHillsPlaced == 0) {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "+";

                            } else {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "-";
                            }
                        }
                    }
                    antHillsPlaced++;
                }
            }
        }

    }

    // Adds *FOOD NEEDED* randomly placed blobs of food into the world.
    // If the map is very small, occasionally it wont be able to place all the food.... So will not terminate. 
    // Try and keep the map bigger than 20x20 ish.
    private void addFood() {
        Random r = new Random();
        int foodNeeded = 3;
        int foodPlaced = 0;
        boolean spaceAvaliable;
        int Low = 0;
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (foodPlaced < foodNeeded) {
            spaceAvaliable = true;
            int spaceResultX = r.nextInt(HighX - Low) + Low;        // Get Random X Coordinate
            int spaceResultY = r.nextInt(HighY - Low) + Low;        // Get Random Y Coordinate

            // Check the blob of food will be placed within the map and on a valid position (all .'s).
            if (spaceResultX > 0 && spaceResultX + 4 < HighX - 1 && spaceResultY > 0 && spaceResultY + 4 < HighY - 1 && spaceAvaliable) {
                for (int counterX = 0; counterX <= 4; counterX++) {
                    for (int counterY = 0; counterY <= 4; counterY++) {
                        spaceAvaliable = world[spaceResultX + counterX][spaceResultY + counterY].equals(".") && spaceAvaliable;
                    }
                }

                // If it is, place the blob of food. 
                if (spaceAvaliable) {
                    for (int counterX = 0; counterX <= 4; counterX++) {
                        for (int counterY = 0; counterY <= 4; counterY++) {
                            world[spaceResultX + counterX][spaceResultY + counterY] = Integer.toString(5);
                        }
                    }
                    foodPlaced++;
                }
            }
        }
    }

    private void addRocks() {
        Random r = new Random();

        int rocksPlaced = 0;
        boolean spaceAvaliable;
        int Low = 0;
        int rocksNeeded = r.nextInt(10 - 7) + 7;    //Random number of rocks between 2 and 10.
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (rocksPlaced < rocksNeeded) {
            spaceAvaliable = true;
            int spaceResultX = r.nextInt(HighX - Low) + Low;        // Get Random X Coordinate
            int spaceResultY = r.nextInt(HighY - Low) + Low;        // Get Random Y Coordinate

            int randomRockX = r.nextInt(r.nextInt(10 - 1) + 1);     // Get Random rock width X
            int randomRockY = r.nextInt(r.nextInt(10 - 1) + 1);     // Get Random rock height Y

            // Check the blob of food will be placed within the map and on a valid position (all .'s).
            if (spaceResultX > 0 && spaceResultX + randomRockX < HighX - 1 && spaceResultY > 0 && spaceResultY + randomRockY < HighY - 1 && spaceAvaliable) {
                for (int counterX = 0; counterX <= randomRockX; counterX++) {
                    for (int counterY = 0; counterY <= randomRockY; counterY++) {
                        spaceAvaliable = world[spaceResultX + counterX][spaceResultY + counterY].equals(".") && spaceAvaliable;
                    }
                }

                // If it is, place the blob of food. 
                if (spaceAvaliable) {
                    for (int counterX = 0; counterX <= randomRockX; counterX++) {
                        for (int counterY = 0; counterY <= randomRockY; counterY++) {
                            if (Math.random() < 0.5) {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "#";
                            }
                        }
                    }
                    rocksPlaced++;
                }
            }
        }
    }

    //returns an int array of the two values x and y respectivly of the cell adjacent to the ant
    //takes a direction as a parameter to show which adjacent cell it needs to return as well as
    //the position of the ant itself
    public int[] adjacentCell(int inx, int iny, int d) {
        int x = inx;
        int y = iny;
        if (d == 0) {
            return new int[]{x + 1, y};
        } else if (d == 1) {
            if (y % 2 == 0) {
                return new int[]{x, y + 1};
            } else {
                return new int[]{x + 1, y + 1};
            }
        } else if (d == 2) {
            if (y % 2 == 0) {
                return new int[]{x - 1, y + 1};
            } else {
                return new int[]{x, y + 1};
            }
        } else if (d == 3) {
            return new int[]{x - 1, y};
        } else if (d == 4) {
            if (y % 2 == 0) {
                return new int[]{x - 1, y - 1};
            } else {
                return new int[]{x, y - 1};
            }
        } else {
            if (y % 2 == 0) {
                return new int[]{x, y - 1};
            } else {
                return new int[]{x + 1, y - 1};
            }
        }
    }

    //Currently, and even number means left and odd means right
    //Note this doesnt change the ants direction, just returns the direction it would be facing
    //if it was to turn that way.
    public int turn(int leftOrRight, int d) {
        if (leftOrRight % 2 == 0) { //if left
            return (d + 5) % 6;
        } else { //else if right
            return (d + 1) % 6;
        }
    }

    public enum senseDir {

        HERE, AHEAD, LEFTAHEAD, RIGHTAHEAD;
    }

    //Currently returns the co-ordianates of the sensed cell entered as a direction in the parameter
    public int[] sensedCell(int[] pos, int dir, senseDir sense) throws Exception {
        int[] result;
        switch (sense) {
            case HERE:
                return pos;
            case AHEAD:
                result = adjacentCell(pos[0], pos[1],  dir);
                return result;
            case LEFTAHEAD:
                int tempDirL = turn(0, dir);
                result = adjacentCell(pos[0], pos[1], tempDirL);
                return result;
            case RIGHTAHEAD:
                int tempDirR = turn(1, dir);
                result = adjacentCell(pos[0], pos[1], tempDirR);
                return result;
            default:
                throw new Exception("Invaid Sense Direction");
        }
    }
    
    public boolean rocky(int[] pos) {
        return world[pos[0]][pos[1]].equals("#");
    }
    
    public int foodAt(int[] pos) {
        return Integer.parseInt(world[0][1]);
    }
    
    public void setFoodAt(int[] pos, int f) {
        world[pos[0]][pos[1]] = Integer.toString(f);
    }
    
    public boolean anthillAt(int[] pos, int colour) {
        if (colour == 0) {
            return (world[pos[0]][pos[1]].equals("+"));
        } else {
            return (world[pos[0]][pos[1]].equals("-"));
        }
    }

    // Prints out the world so it can be viewed.
    void testWorld() {
        System.out.println("World Width = " + getMapWidth());
        System.out.println("World Height = " + getMapHeight());

        String printTest = "";

        for (int i = 0; i < mapHeight; i++) {

            for (int j = 0; j < mapWidth; j++) {
                printTest = printTest + world[i][j];
            }
            if (i % 2 == 0) {
                System.out.println(" " + printTest);
            } else {
                System.out.println(printTest);
            }
            printTest = "";
        }
    }

}
