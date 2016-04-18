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

    // Adds 11 randomly placed blobs of food into the world.
    private void addFood() {
        Random r = new Random();
        int foodNeeded = 11;
        int foodPlaced = 0;

        int Low = 0;
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (foodPlaced < foodNeeded) {
            int spaceResultX = r.nextInt(HighX - Low) + Low;
            int spaceResultY = r.nextInt(HighY - Low) + Low;
            if (world[spaceResultX][spaceResultY].equals(".")) {
                int randomFoodAmount = r.nextInt(10 - 1) + 1;
                world[spaceResultX][spaceResultY] = Integer.toString(randomFoodAmount);
                foodPlaced++;
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