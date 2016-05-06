/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.World;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harry
 */
public class WorldTest {

    public WorldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testWorldInput() {
        World numbers = new World(100, 100);
        assertEquals(numbers.getMapHeight(), 100);
        assertEquals(numbers.getMapWidth(), 100);
    }

    @Test
    public void testRocksOnOutside() {
        World testWorld = new World(100, 100);
        boolean isCorrect = true;

        for (int i = 0; i < testWorld.getMapWidth(); i++) {
            for (int j = 0; j < testWorld.getMapHeight(); j++) {
                if (i == 0 || j == 0 || i == testWorld.getMapWidth() - 1 || j == testWorld.getMapHeight() - 1) {
                    isCorrect = ("#".equals(testWorld.getCellData(i, j)));
                }
            }
        }
    }
}
