/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harry
 */
public class WorldParser {
    int x;
    int y;
    public String[][] world;
    
    public String[][] WorldParser(File f) throws FileNotFoundException{
        try {
            
            // Import a file to be parsed.

            Scanner input = new Scanner(new FileReader(f));
            x = Integer.parseInt(input.nextLine());
            System.out.println("X = " + x);
            y = Integer.parseInt(input.nextLine());
            System.out.println("Y = " + y);
            
            
            world = new String[x][y];
            
            //String line = input.nextLine();
            int rowCounter = 0;
            while(input.hasNextLine()){
                int charCounter = 0;
                Scanner rowScanner = new Scanner(input.nextLine());
                while(rowScanner.hasNext()){
                    world[rowCounter][charCounter] = rowScanner.next();
                    charCounter ++;
                }
                rowCounter ++;
            }
            
            for(int i = 0; i < x; i ++){
                for(int j = 0; j < y; j ++){
                    System.out.print(world[i][j]);
                }
                System.out.println(" ");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(WorldParser.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return world;
        
    }
        
}
