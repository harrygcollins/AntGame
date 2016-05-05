package Tests;

import antgame.Ant;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dylan
 */
public class AntTest {
    public Ant ant1;
    public Ant ant2;
    
    public AntTest() {
        ant1 = new Ant(0, 5, 5, 1); //Red team ant at position (5,5) with id: 1
        ant2 = new Ant(1, 1, 7, 6); //Black team ant at position (1,7) with id: 6
    }
    
    @Test
    public void positionTests() {
        
        int[] pos = ant1.getPos();
        
        assertEquals(pos[0],5);
        assertEquals(pos[1],5);
        
        ant1.setPosition(6, 9);
        
        assertEquals(ant1.getAntX(), 6);
        assertEquals(ant1.getAntY(), 9);
    }
    
    @Test
    public void directionTest() {
        
        int dir = ant2.getDirection();
        assertEquals(dir, 0);
        
        ant2.setDirection("left");
        assertEquals(ant2.getDirection(),5);
    }
    
    @Test
    public void foodTest() {
        
        ant1.setHasFood(true);
        assertTrue(ant1.getHasFood());
        
    }
    
    
    
}
