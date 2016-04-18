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
    int colour;           // 0 for Red, 1 for Black
    int id;
    int state;
    int resting;
    
    boolean hasFood;
    
    public Ant(int colour, int startingX, int startingY, int id){
        direction = 0;      // Facing East
        this.colour = colour;
        this.positionX = startingX;
        this.positionY = startingY;
        this.id = id;
        hasFood = false;
        resting = 0;
        
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
    
    public boolean getHasFood() {
        return hasFood;
    }
    
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public int getColour() {
        return colour;
    }
    
    public int getResting() {
        return resting;
    }
    
    public void setResting(int resting) {
        this.resting = resting;
    }
    
    //returns int value of the other colour
    public int otherColour() {
        return Math.abs(colour-1);
    }
    
    //Currently, and even number means left and odd means right
    public void turn(int leftOrRight) {
        if (leftOrRight%2 == 0) { //if left
            direction = (direction + 5) % 6;
        } else { //else if right
            direction = (direction + 1) % 6;
        }
    }
    
    //returns an int array of the two values x and y respectivly of the cell adjacent to the ant
    //takes a dirrection as a parameter to show which adjacent cell it needs to return
    public int[] adjacentCell(int d) {
        int x = getAntX();
        int y = getAntY();
        if (d == 0) {
            return new int[] {x+1,y};
        } else if (d == 1) {
            if (y % 2 == 0) {
                return new int[] {x,y+1};
            } else {
                return new int[] {x+1,y+1};
            }
        } else if (d == 2) {
            if (y % 2 == 0) {
                return new int[] {x-1,y+1};
            } else {
                return new int[] {x,y+1};
            }
        } else if (d == 3) {
            return new int[] {x-1,y};
        } else if (d == 4) {
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
    
    //Currently returns the co-ordianates of the sensed cell entered as a direction in the parameter
    //0 means Here, 1 means Ahead, 2 means LeftAhead and 3 means RightAhead
    public int[] sensedCell(int senseDir) {
        int[] result;
        if (senseDir == 0) { //Here
            result = new int[] {getAntX(),getAntY()};
        } else if (senseDir == 1) { //Ahead
            result = adjacentCell(direction);
        } else if (senseDir == 2) { //LeftAhead
            turn(0);
            result = adjacentCell(direction);
            turn(1);
        } else { //RightAhead
            turn(1);
            result = adjacentCell(direction);
            turn(0);
        }
        return result;
    }
    
}
