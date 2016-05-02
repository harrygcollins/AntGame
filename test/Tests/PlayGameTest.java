/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.PlayGame;
import antgame.World;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harry
 */
public class PlayGameTest {
    
    public PlayGameTest() {
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
    public void wholeTest() {
        File f1 = new File("ANTBRAIN");
        File f2 = new File("ANTBRAIN");
        
        World world = new World(50, 50);
        
        PlayGame game = new PlayGame(f1, f2, world);
        int winner = game.runGame();
        
        if(winner == 0 ){
            System.out.println(" RED WINS ");
        } else if (winner == 1) {
            System.out.println(" DRAW ");
        } else if (winner == 2) {
            System.out.println(" BLACK WINS ");
        } else {
            System.out.println(" Something's gone horribly wrong... ");
        }
    
    }
}
