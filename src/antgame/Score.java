package antgame;

import java.io.Serializable;

/**
 * The score class creates a high score object that can be put into an array 
 * list and saved.
 * Extends Comparable to sort the scores.
 * @author Team 13
 */
public class Score implements Comparable<Score>{
    private String teamName;
    private int score;
    

    /**
     * Constructor for the score class.
     * @param teamName the Name of the player.
     * @param score the score of the player.
     */
    public Score(String teamName, int score) {
        this.score = score;
        this.teamName = teamName;
    }
    
    /**
     * Returns the score  of the team.
     * @return the score of the team in the score object.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the name of the team.
     * @return the name of the team in the score object.
     */
    public String getTeamName() {
        return teamName;
    }
    
     /**
     * @param s an integer to set the score of the objects team
     */
    public void setScore(int s){
        score = s;
    }

    @Override
    public int compareTo(Score o) {
        return ((Integer)(o.getScore())).compareTo(getScore());
    }
    
}
