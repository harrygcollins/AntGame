/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author Team13
 */
import java.io.*;

public class AntGame {


    
    public final static void main(String[] argv) throws IOException {

        String buffer;
        int mapWidth;
        int mapHeight;
        World world;

        // initialise a BufferedReader to read from the standard input (keyboard)
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ant Game");

        // Allow the user to input the width of the Map
        System.out.print("Enter the width, x, of the map (> 0): ");
        buffer = stdIn.readLine();
        mapWidth = Integer.parseInt(buffer);
        
        // Throw an error if an invalid input is entered.
        if (mapWidth <= 0) {
            System.err.println("Width must be > 0");
            System.exit(-1);
        }

        // Allow the user to input the height of the Map
        System.out.print("Enter the hight, y, of the map (> 0): ");
        buffer = stdIn.readLine();
        mapHeight = Integer.parseInt(buffer);
        
        // Throw an error if an invalid input is entered.
        if (mapHeight <= 0) {
            System.err.println("Height must be > 0");
            System.exit(-1);
        }
        
        world = new World(mapWidth, mapHeight);
        
    }
    
}
