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
    private void addAntHill(){
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
                            if(antHillsPlaced == 0){
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
                            if(Math.random() < 0.5) {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "#";
                            }
                        }
                    }
                    rocksPlaced++;
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
