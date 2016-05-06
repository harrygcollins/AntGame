/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.World;
import antgame.WorldParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harry
 */
public class WorldParserTest {

    public WorldParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void worldParseTest() {

        WorldParser wParser = new WorldParser();
        try {
            World world = wParser.WorldParser(new File("WorldParseTest"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorldParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
