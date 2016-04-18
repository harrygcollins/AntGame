/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.RandomNumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harry
 */
public class RandomNumberTest {
    
    public RandomNumberTest() {
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
    //Tests teh first 5 results to those given in the specification
    @Test
    public void randomTest() {
        RandomNumber rn = new RandomNumber(12345); //set up with given seed
        int n = 16383; //given value of n to be used
        assertEquals(7193,rn.randomInt(n));
        assertEquals(2932,rn.randomInt(n));
        assertEquals(10386,rn.randomInt(n));
        assertEquals(5575,rn.randomInt(n));
        assertEquals(100,rn.randomInt(n));
    }
}
