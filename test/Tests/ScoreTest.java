/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import antgame.Score;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADigenis
 */
public class ScoreTest {

    public ScoreTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getScore method, of class Score.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Score s = new Score("team13", 10);
        int expResult = 10;
        int result = s.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeamName method, of class Score.
     */
    @Test
    public void testGetTeamName() {
        System.out.println("getTeamName");
        Score s = new Score("team13", 10);
        String expResult = "team13";
        String result = s.getTeamName();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Score.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Score s1 = new Score("team13", 10);
        Score s2 = new Score("team1", 4);
        int expResult = 1;
        int result = s2.compareTo(s1);
        assertEquals(expResult, result);
    }

}
