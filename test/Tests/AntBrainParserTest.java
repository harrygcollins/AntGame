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
    public void testListMaker(){
        AntBrainParser parser = new AntBrainParser();
        for (List<String> brainList : parser.brainList) {
            for (String brainList1 : brainList) {
                System.out.println(brainList1);
            }
        }
    }
    
    
    @Test
    public void firstTest() {
        // Create the class
        AntBrainParser parser = new AntBrainParser();
        
        // Create the outer and inner array (basically 2D arraylist)
        List<List<String>> testBrain = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        
        // Add two strings to (0, 0) and (0,1) coordinates. 
        tempList.add("drop");
        tempList.add("5");
        tempList.add("; Notes which shouldn't parse");
        
        // Add the inner array list into the outer one.
        testBrain.add(tempList);
        
        // See if this is valid
        assertTrue(parser.IsValidBrain(testBrain));
    }
    
    @Test
    public void falseTest(){
        AntBrainParser parser = new AntBrainParser();
        List<List<String>> testBrain = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        tempList.add("harry");
        tempList.add("5");
        testBrain.add(tempList); //added this line to fix test, please check!
        assertFalse(parser.IsValidBrain(testBrain));
    }
    
    @Test
    public void fullTest(){
        AntBrainParser parser = new AntBrainParser();
        assertTrue(parser.IsValidBrain(parser.brainList));
        
    }
}
