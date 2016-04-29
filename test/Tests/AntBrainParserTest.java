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
        for(int i = 0; i < parser.brainList.size(); i++){
            for(int j = 0; j < parser.brainList.get(i).size(); j++){
                System.out.println(parser.brainList.get(i).get(j));
            }
        }
    }
    
    
    @Test
    public void firsTest() {
        AntBrainParser parser = new AntBrainParser();
        List<List<String>> testBrain = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        tempList.add("Drop");
        tempList.add("5");
        testBrain.add(tempList);
        assertTrue(parser.IsValidBrain(testBrain));
    }
    
    @Test
    public void falseTest(){
        AntBrainParser parser = new AntBrainParser();
        List<List<String>> testBrain = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        tempList.add("harry");
        tempList.add("5");
        assertFalse(parser.IsValidBrain(testBrain));
    }
    
    @Test
    public void fullTest(){
        AntBrainParser parser = new AntBrainParser();
        assertTrue(parser.IsValidBrain(parser.brainList));
        
    }
}
