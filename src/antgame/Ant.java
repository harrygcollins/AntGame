/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author Harry
 */
public class Ant {
    
    public int direction;
    int positionX;
    int positionY;
    boolean team;           // True for Red, False for Black
    int id;
    
    boolean hasFood;
    
    public Ant(boolean Team, int startingX, int startingY, int id){
        direction = 0;      // Facing East
        this.team = Team;
        this.positionX = startingX;
        this.positionY = startingY;
        this.id = id;
        
    }
    
    public int getAntX(){
        return positionX;
    }
    
    public int getAntY(){
        return positionY;
    }
    
    public int getDirection() {
        return direction;
    }
    
    //Currently, true means left and false means right but this will most likely be changed
    public void turn(boolean leftOrRight) {
        if (leftOrRight) { //if left
            direction = (direction + 5) % 6;
        } else { //else if right
            direction = (direction + 1) % 6;
        }
    }
    
    //returns an int array of the two values x and y respectivly of the cell adjacent to the ant
    public int[] adjacent_cell() {
        int x = getAntX();
        int y = getAntY();
        if (direction == 0) {
            return new int[] {x+1,y};
        } else if (direction == 1) {
            if (y % 2 == 0) {
                return new int[] {x,y+1};
            } else {
                return new int[] {x+1,y+1};
            }
        } else if (direction == 2) {
            if (y % 2 == 0) {
                return new int[] {x-1,y+1};
            } else {
                return new int[] {x,y+1};
            }
        } else if (direction == 3) {
            return new int[] {x-1,y};
        } else if (direction == 4) {
            if (y % 2 == 0) {
                return new int[] {x-1,y-1};
            } else {
                return new int[] {x,y-1};
            }
        } else {
            if (y % 2 == 0) {
                return new int[] {x,y-1};
            } else {
                return new int[] {x+1,y-1};
            }
        }
    }
    
}