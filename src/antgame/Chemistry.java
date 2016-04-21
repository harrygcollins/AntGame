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

    public enum markerType {

        BASE, WARNING, FriendWithFood, FOOD, EDGE, DIED;
    }

    class markers {

        markers(markerType m, int colour) {
            int team;
            markerType type;
           
            team = colour;
            type = m;


        }
    }

        public int ChooseMarker(markerType m) throws Exception {

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

        public void placeMarker(Ant a){
            int x, y;
            x = a.positionX;
            y = a.positionY;
            
            
                    
        //Check cell to see if it is available
            // if its available:
                    // create a marker object with the marker type and colour
                    // place marker on board at given co-ordinates
           // if the cell is taken run clearMarker method
       

    }

public void clearMarker() {
//        1.Sense Cell
//        2.if cell contains a marker object from other team marker remove the object
//        3.markers are checked for the colour of origin o prevent removal of own teams markers which is stored in the marker
        }
    }

