package antgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to parse a world to check it is correct and usable in a game.
 *
 * @author Team 13
 */
public class WorldParser {

    int x;
    int y;
    public String[][] world;
    World newWorld;

    public World WorldParser(File f) throws FileNotFoundException {
        try {

            // Import a file to be parsed.
            Scanner input = new Scanner(new FileReader(f));
            x = Integer.parseInt(input.nextLine());
            y = Integer.parseInt(input.nextLine());

            world = new String[x][y];
            int rowCounter = 0;
            while (input.hasNextLine()) {
                int charCounter = 0;
                Scanner rowScanner = new Scanner(input.nextLine());
                while (rowScanner.hasNext()) {
                    world[rowCounter][charCounter] = rowScanner.next();
                    charCounter++;
                }
                rowCounter++;
            }

        } catch (IOException ex) {
            Logger.getLogger(WorldParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        newWorld = new World(x, y);
        newWorld.setWorld(world);
        return newWorld;

    }

}
