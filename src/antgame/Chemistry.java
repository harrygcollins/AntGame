/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author jc573
 */
public class Chemistry {

    public int typeChoice = 0;

    public enum markerType {

        BASE, WARNING, FriendWithFood, FOOD, EDGE, DIED;
  //    0     1        2               3      4     5
    }

   

    public int chooseMarker(markerType m) throws Exception {

        switch (m) {
            case BASE:
                return 0;

            case WARNING:
                return 1;

            case FriendWithFood:
                return 2;

            case FOOD:
                return 3;

            case EDGE:
                return 4;

            case DIED:
                return 5;

            default:
                throw new Exception("Invaid Marker");

        }
    }

    public void placeMarker(Ant a, World w) {
        int x, y;
        boolean taken;
        taken = false;
        x = a.positionX;
        y = a.positionY;
        // Please Clarrify or add
        // This method must now check the cell at the ants location to ensure no marker is placed
        // if sensed cell has a marker check marker.team to see if it belongs to the enemy if it does  
        // run remove method

//        if (taken) {
//            clearMarker(x, y);
//        }


        //  Else create marker, value must be that returned from Choosing Type Method
        //typeChoice = chooseMarker(markerType);

        markers marker;
        marker = new markers(typeChoice, a.colour);
        //place object on world
        
        
  
    }
    
    
    
    
    
    // markers object to be placed on board
    public class markers {
        public int team;
        public int type;

        markers(int markerType, int colour) {
            team = colour;
            type = markerType;
        }
    }

    public void clearMarker(int x, int y) {
      //        remove enemy marker from world
    }
    
    public boolean cellMatches(int[] p, markerType cond, int colour) {
        switch (cond) {
            //case Friend:
                //some_ant_is_at(p) && colour(ant_at(p)) == c
            //case Foe:
                //some_ant_is_at(p) && colour(ant_at(p)) !== c
            //case FriendWithFood:
                //some_ant_is_at(p) && colour(ant_at(p)) == c && has_food(ant_at(p))
            //case FoeWithFood:
                //some_ant_is_at(p) && colour(ant_at(p)) !== c && has_food(ant_at(p))
            //case Food:
                //food_at(p) > 0
            //case Rock:
                //isRocky(p) = true
            //case Marker(i):
                //check_marker_at(p,c,i)
            //case FoeMarker:
                //check_any_marker_at(p, other_colour(c))
            //case Home:
                //anthill_at(p, c)
            //case FoeHome:
                //anthill_at(p, other_colour(c))
        }
        return false;
    }
}
