/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.AntBrainParser;
import antgame.PlayGame;
import antgame.World;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        
        List<List<String>> brainList1 = new ArrayList<>();
        List<List<String>> brainList2 = new ArrayList<>();
        
        File f1 = new File("ANTBRAIN");
        File f2 = new File("ANTBRAIN");
        
        World world = new World(50, 50);
        AntBrainParser parser = new AntBrainParser();
        
        brainList1 = parser.AntBrainParser(f1);
        brainList2 = parser.AntBrainParser(f2);
        
        PlayGame game = new PlayGame(brainList1, brainList2, world);
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
