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
        // Add Ant hill
        // Add rocks
        addFood();

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
