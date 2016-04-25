/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.AntBrainParser;
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
public class AntBrainParserTest {
    
    public AntBrainParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void firsTest() {
        AntBrainParser parser = new AntBrainParser();
        List<String> testBrain = new ArrayList<String>();
        testBrain.add("drop");
        testBrain.add("5");
        assertTrue(parser.IsValidBrain(testBrain));
    }
    
    @Test
    public void falseTest(){
        AntBrainParser parser = new AntBrainParser();
        List<String> testBrain = new ArrayList<String>();
        testBrain.add("harry");
        testBrain.add("5");
        assertFalse(parser.IsValidBrain(testBrain));
    }
    
    @Test
    public void fullTest(){
        AntBrainParser parser = new AntBrainParser();
        assertTrue(parser.IsValidBrain(parser.token));
        
    }
}
