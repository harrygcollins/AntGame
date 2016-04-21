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
Ant a;
    public enum markerType {

        BASE, WARNING, FriendWithFood, FOOD, EDGE, DIED;
    }

    public int placeMarker(markerType m) throws Exception {
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
                throw new Exception("Invaid Case");

        }
        


    }

    public void clearMarker() {
//        1.Sense Cell
//        2.if cell contains a marker object from other team marker remove the object
//        3.markers are checked for the colour of origin which is stored in the marker
    }
    class marker{
        
       
        marker(markerType m, int x , int y, int c){
            
        }
    }
}
