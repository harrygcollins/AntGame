package antgame;

/**
  * This class represents the individual entity of an Ant in a game. They have various
  * variables used to define the characteristics of the ant object as there will be
  * multiple instances in the game at once.
  * 
  * @author Team 13
 */
public class Ant {
    
    public int direction; //which direction the ant is facing (0...5)
    int positionX; //the x coordinate of the ant
    int positionY; //the y coordinate of the ant
    int colour; // 0 for Red, 1 for Black
    int id; //the identification number for the ant
    int state; //the state of the ant
    int resting; //how long it has to rest for
    boolean hasFood; //true if the ant has food, false otherwise
    boolean isDead; //true if the ant has been killed, false otherwise
    
    /**
     * The constructor for an Ant.
     * @param colour an integer representing which team the ant belongs to
     * @param startingX an integer representing the starting x coordinate of the Ant
     * @param startingY an integer representing the starting y coordinate of the Ant
     * @param id an integer representing the id of the Ant
    */
    public Ant(int colour, int startingX, int startingY, int id){
        direction = 0;
        this.colour = colour;
        this.positionX = startingX;
        this.positionY = startingY;
        this.id = id;
        hasFood = false;
        resting = 0;
        isDead = false;
        state = 0;
        
    }
    
    /**
     * @return an integer array containing the x and y coordinate of the ant respectively
    */
    public int[] getPos() {
        return new int[] {positionX, positionY};
    }
    
    /**
     * @return an integer representing the x coordinate of the ant
    */
    public int getAntX(){
        return positionX;
    }
    
    /**
     * @return an integer representing the y coordinate of the ant
    */
    public int getAntY(){
        return positionY;
    }
    
    /**
     * @return an integer representing the direction the ant is facing
    */
    public int getDirection() {
        return direction;
    }
    
    /**
     * @return a boolean, true if the ant has food, false otherwise
    */
    public boolean getHasFood() {
        return hasFood;
    }
    
    /**
     * @param hasFood a boolean value to assign to the ants hasFood attribute
    */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }
    
    /**
     * @return the integer representing the ants state
    */
    public int getState() {
        return state;
    }
    
    /**
     * @param state an integer representing the new state to be assigned to the ant
    */
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * @return an integer representing the colour of the ant (which team it's on)
    */
    public int getColour() {
        return colour;
    }
    
    /**
     * @return an integer representing the resting time of the ant
    */
    public int getResting() {
        return resting;
    }
    
    /**
     * @param resting an integer representing the new resting time to be assigned
    */
    public void setResting(int resting) {
        this.resting = resting;
    }
    
    /**
     * @return an boolean value representing if the ant is alive or dead
    */
    public boolean getIsDead(){
        return isDead;
    }
    
    /**
     * @param deadOrAlive a boolean representing the new isDead value for the ant
    */
    public void setIsDead(boolean deadOrAlive){
        isDead = deadOrAlive;
    }
    
    /**
     * @param dir a string representing the choice to turn either left or right
    */
    public void setDirection(String dir){
        if(dir.equals("right")){
            direction = turn(1);
        } else {
            direction = turn(2);
        }
    }
    
    /**
     * @param x an integer representing the new x coordinate of the ant
     * @param y an integer representing the new y coordinate of the ant
    */
    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;
    }
    
    /**
     * @param leftOrRight an integer when even represents left, otherwise right
     * @return an integer representing the new direction the ant would be facing
    */
    public int turn(int leftOrRight) {
        if (leftOrRight%2 == 0) { //if left
           return (direction + 5) % 6;
        } else { //else if right
            return (direction + 1) % 6;
        }
    }
}
