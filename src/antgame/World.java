package antgame;

import java.util.Random;

/**
 * This class represents the map or the world which a game is played on.
 * It keeps track of what the map looks like and creates and tests new ones.
 *
 * @author Team 13
 */
public class World {

    private static World instance = null;
    private int mapWidth;
    private int mapHeight;
    private String[][] world;

    /**
    * Constructor for the World.
    * @param MapWidth an integer representing the width of the map
    * @param MapHeight an integer representing the height of the map
    */
    public World(int MapWidth, int MapHeight) {
        this.mapWidth = MapWidth;
        this.mapHeight = MapHeight;

        world = new String[mapWidth][mapHeight];

        addBorder();
        addAntHill();
        addFood();
        addRocks();

        testWorld();
    }

    // If you want to use the world, use the getInstance() method.
    public static World getInstance() {
        return instance;
    }

    /**
     * @return an integer representing the map height
    */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * @return an integer representing the map width
    */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * @param x an integer representing the x coordinate of the position
     * @param y an integer representing the y coordinate of the position
     * @return a string representing the cell at the given position in the map
    */
    public String getCellData(int x, int y) {
        return world[x][y];
    }

    /**
     * @param w a list of list of strings representing a world
    */
    public void setWorld(String w[][]){
        world = w;
    }
    
    /**
     * @param x an integer representing the x coordinate of the position
     * @param y an integer representing the y coordinate of the position
     * @return an integer representing the amount of food at the given position
    */
    public int getFoodAt(int x, int y) {
        if (isInteger(world[x][y])) {
            return Integer.parseInt(world[x][y]);
        } else {
            return -1;
        }
    }

    /**
     * @param x an integer representing the x coordinate of the position
     * @param y an integer representing the y coordinate of the position
     * @param amount an integer value representing the amount of food to be set
    */
    public void setFoodAt(int x, int y, int amount) {
        if (isInteger(world[x][y])) {
            world[x][y] = Integer.toString(Integer.parseInt(world[x][y]) + amount);
        } else if (world[x][y] == ".") {
            world[x][y] = Integer.toString(amount);
        }
    }

    /**
     * @param s a string
     * @return a boolean true if string is an integer
    */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    /**
    // Adds a border to the world and fills the inside with .'s.
    */
    private void addBorder() {
        for (int i = 0; i < mapHeight; i++) {

            for (int j = 0; j < getMapWidth(); j++) {
                if (i == 0 || i == mapHeight - 1 || j == mapWidth - 1 || j == 0) {
                    world[i][j] = "#";
                } else {
                    world[i][j] = ".";
                }
            }

        }
    }

    /**
    * Adds two randomly placed ant hills into the world.
    */
    private void addAntHill() {
        int antHillX = 7;
        int antHillY = 7;
        Random r = new Random();
        int antHillsNeeded = 2;
        int antHillsPlaced = 0;
        boolean spaceAvaliable;
        int Low = 0;
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (antHillsPlaced < antHillsNeeded) {
            spaceAvaliable = true;
            int spaceResultX = r.nextInt(HighX - Low) + Low;        // Get Random X Coordinate
            int spaceResultY = r.nextInt(HighY - Low) + Low;        // Get Random Y Coordinate

            // Check the antHill will be placed within the map and on a valid position (all .'s).
            if (spaceResultX > 0 && (spaceResultX + antHillX - 1) < HighX - 1 && spaceResultY > 0 && (spaceResultY + antHillY - 1) < HighY - 1 && spaceAvaliable) {
                for (int counterX = 0; counterX <= 4; counterX++) {
                    for (int counterY = 0; counterY <= 4; counterY++) {
                        spaceAvaliable = world[spaceResultX + counterX][spaceResultY + counterY].equals(".") && spaceAvaliable;
                    }
                }

                // If it is, place the antHill food. 
                if (spaceAvaliable) {
                    for (int counterX = 0; counterX < antHillX; counterX++) {
                        for (int counterY = 0; counterY < antHillY; counterY++) {
                            if (antHillsPlaced == 0) {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "+";

                            } else {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "-";
                            }
                        }
                    }
                    antHillsPlaced++;
                }
            }
        }

    }
    
    /**
    * Adds *FOOD NEEDED* randomly placed blobs of food into the world.
    * If the map is very small, occasionally it wont be able to place all the food.... So will not terminate. 
    * Try and keep the map bigger than 20x20.
    */
    private void addFood() {
        Random r = new Random();
        int foodNeeded = 11;
        int foodPlaced = 0;
        boolean spaceAvaliable;
        int Low = 0;
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (foodPlaced < foodNeeded) {
            spaceAvaliable = true;
            int spaceResultX = r.nextInt(HighX - Low) + Low;        // Get Random X Coordinate
            int spaceResultY = r.nextInt(HighY - Low) + Low;        // Get Random Y Coordinate

            // Check the blob of food will be placed within the map and on a valid position (all .'s).
            if (spaceResultX > 0 && spaceResultX + 4 < HighX - 1 && spaceResultY > 0 && spaceResultY + 4 < HighY - 1 && spaceAvaliable) {
                for (int counterX = 0; counterX <= 4; counterX++) {
                    for (int counterY = 0; counterY <= 4; counterY++) {
                        spaceAvaliable = world[spaceResultX + counterX][spaceResultY + counterY].equals(".") && spaceAvaliable;
                    }
                }

                // If it is, place the blob of food. 
                if (spaceAvaliable) {
                    for (int counterX = 0; counterX <= 4; counterX++) {
                        for (int counterY = 0; counterY <= 4; counterY++) {
                            world[spaceResultX + counterX][spaceResultY + counterY] = Integer.toString(5);
                        }
                    }
                    foodPlaced++;
                }
            }
        }
    }

    /**
     * Add random rocks to the map.
    */
    private void addRocks() {
        Random r = new Random();

        int rocksPlaced = 0;
        boolean spaceAvaliable;
        int Low = 0;
        int rocksNeeded = r.nextInt(10 - 7) + 7;    //Random number of rocks between 2 and 10.
        int HighX = getMapWidth();
        int HighY = getMapHeight();

        while (rocksPlaced < rocksNeeded) {
            spaceAvaliable = true;
            int spaceResultX = r.nextInt(HighX - Low) + Low;        // Get Random X Coordinate
            int spaceResultY = r.nextInt(HighY - Low) + Low;        // Get Random Y Coordinate

            int randomRockX = r.nextInt(r.nextInt(10 - 1) + 1);     // Get Random rock width X
            int randomRockY = r.nextInt(r.nextInt(10 - 1) + 1);     // Get Random rock height Y

            // Check the blob of food will be placed within the map and on a valid position (all .'s).
            if (spaceResultX > 0 && spaceResultX + randomRockX < HighX - 1 && spaceResultY > 0 && spaceResultY + randomRockY < HighY - 1 && spaceAvaliable) {
                for (int counterX = 0; counterX <= randomRockX; counterX++) {
                    for (int counterY = 0; counterY <= randomRockY; counterY++) {
                        spaceAvaliable = world[spaceResultX + counterX][spaceResultY + counterY].equals(".") && spaceAvaliable;
                    }
                }

                // If it is, place the blob of food. 
                if (spaceAvaliable) {
                    for (int counterX = 0; counterX <= randomRockX; counterX++) {
                        for (int counterY = 0; counterY <= randomRockY; counterY++) {
                            if (Math.random() > 0.3) {
                                world[spaceResultX + counterX][spaceResultY + counterY] = "#";
                            }
                        }
                    }
                    rocksPlaced++;
                }
            }
        }
    }
    
    /**
     * @param inx the x coordinate of the position
     * @param iny the y coordinate of the position
     * @param d the direction the ant is facing
     * @return an integer array of the two values x and y respectively of the cell adjacent to the ant
    */
    public int[] adjacentCell(int inx, int iny, int d) {
        int x = inx;
        int y = iny;
        if (d == 0) {
            return new int[]{x + 1, y};
        } else if (d == 1) {
            if (y % 2 == 0) {
                return new int[]{x, y + 1};
            } else {
                return new int[]{x + 1, y + 1};
            }
        } else if (d == 2) {
            if (y % 2 == 0) {
                return new int[]{x - 1, y + 1};
            } else {
                return new int[]{x, y + 1};
            }
        } else if (d == 3) {
            return new int[]{x - 1, y};
        } else if (d == 4) {
            if (y % 2 == 0) {
                return new int[]{x - 1, y - 1};
            } else {
                return new int[]{x, y - 1};
            }
        } else {
            if (y % 2 == 0) {
                return new int[]{x, y - 1};
            } else {
                return new int[]{x + 1, y - 1};
            }
        }
    }

    /**
     * @param leftOrRight an integer representing the choice to turn either direction
     * @param d the current direction of the ant
     * @return the new direction the ant would face
     */
    public int turn(int leftOrRight, int d) {
        if (leftOrRight % 2 == 0) { //if left
            return (d + 5) % 6;
        } else { //else if right
            return (d + 1) % 6;
        }
    }

    /**
     * @param pos the current position of the ant
     * @param dir the direction the ant is facing
     * @param sense the direction the ant wishes to sense
     * @return the position of the cell the ant wants to sense
     * @throws Exception 
     */
    public int[] sensedCell(int[] pos, int dir, senseDir sense) throws Exception {
        int[] result;
        switch (sense) {
            case HERE:
                return pos;
            case AHEAD:
                result = adjacentCell(pos[0], pos[1], dir);
                return result;
            case LEFTAHEAD:
                int tempDirL = turn(0, dir);
                result = adjacentCell(pos[0], pos[1], tempDirL);
                return result;
            case RIGHTAHEAD:
                int tempDirR = turn(1, dir);
                result = adjacentCell(pos[0], pos[1], tempDirR);
                return result;
            default:
                throw new Exception("Invaid Sense Direction");
        }
    }

    /**
     * @param pos the coordinates of a position on the board
     * @return true if the cell is rocky, false otherwise
     */
    public boolean rocky(int[] pos) {
        return world[pos[0]][pos[1]].equals("#");
    }

    /**
     * @param pos position on board to be changed
     * @param f the integer to change it to
     */
    public void setFoodAt(int[] pos, int f) {
        world[pos[0]][pos[1]] = Integer.toString(f);
    }

    /**
     * @param pos position on the board
     * @param colour the team colour the anthill should belong to
     * @return true if that teams anthill is at that position
     */
    public boolean anthillAt(int[] pos, int colour) {
        if (colour == 0) {
            return (world[pos[0]][pos[1]].equals("+"));
        } else {
            return (world[pos[0]][pos[1]].equals("-"));
        }
    }

    /**
     * @param s the marker choice
     * @param a the ant choosing the marker
     * @return the string used to store the marker on the world
     * @throws Exception 
     */
    public String chooseMarker(int s, Ant a) throws Exception {

        switch (s) {
            //case BASE:
            case 1:
                if (a.colour == 0) {
                    return "Q";
                } else {
                    return "W";
                }
            case 2:
                //case WARNING:
                if (a.colour == 0) {
                    return "E";
                } else {
                    return "R";
                }
            case 3:
                //case FriendWithFood:
                if (a.colour == 0) {
                    return "T";
                } else {
                    return "Y";
                }
            case 4:
                //case FOOD:
                if (a.colour == 0) {
                    return "Z";
                } else {
                    return "X";
                }
            case 5:
                //case EDGE:
                if (a.colour == 0) {
                    return "C";
                } else {
                    return "V";
                }
            case 6:
                //case DIED:
                if (a.colour == 0) {
                    return "B";
                } else {
                    return "N";
                }

            default:
                throw new Exception("Invaid Marker");
        }
    }

    /**
     * Clears the marker at the ants position.
     * @param a the ant at the position of the marker
     */
    public void clearMarker(Ant a) {
        int x = a.getAntX();
        int y = a.getAntY();
        String cellValue = world[x][y];
        if (a.colour != 0) {
            if (cellValue == "W" || cellValue == "R" || cellValue == "Y" || cellValue == "X" || cellValue == "V" || cellValue == "N") {
                world[x][y] = ".";
            }
        } else if (cellValue == "Q" || cellValue == "E" || cellValue == "T" || cellValue == "Z" || cellValue == "C" || cellValue == "B") {
            world[x][y] = ".";
        }
    }

    /**
     * Places the marker at the ants position
     * @param a the ant placing the marker
     * @param i the marker choice to be placed
     * @throws Exception 
     */
    public void placeMarker(Ant a, int i) throws Exception {
        int x, y;
        Ant thisAnt = a;
        int marker = i;
        x = a.positionX;
        y = a.positionY;
        String cellValue = world[x][y];
        // Please Clarrify or add
        // This method must now check the cell at the ants location to ensure no marker is placed
        // if sensed cell has a marker check marker.team to see if it belongs to the enemy if it does  
        // run remove method

        // If there is no marker and it is a free space, place the marker. 
        if (world[x][y] == ".") {
            world[x][y] = chooseMarker(marker, a);
        } // Else, check if there is currently an enemy marker, if so, remove it. 
        else if (a.colour != 0) {
            if (cellValue == "W" || cellValue == "R" || cellValue == "Y" || cellValue == "X" || cellValue == "V" || cellValue == "N") {
                clearMarker(a);
            }
        } else if (cellValue == "Q" || cellValue == "E" || cellValue == "T" || cellValue == "Z" || cellValue == "C" || cellValue == "B") {
            clearMarker(a);
        } else {
            // Do nothing (I know this is bad but I dont want an exception).
        }
    }

// Prints out the world so it can be viewed.
    public String testWorld() {
        System.out.println("World Width = " + getMapWidth());
        System.out.println("World Height = " + getMapHeight());

        String printTest = "";
        String w;
        String t = "";
        for (int i = 0; i < mapHeight; i++) {

            for (int j = 0; j < mapWidth; j++) {
                printTest = printTest + world[i][j];
            }
            if (i % 2 == 0) {
                System.out.println(" " + printTest);
                w = " " + printTest;
            } else {
                System.out.println(printTest);
                w = printTest;
            }
            printTest = "";
            t += w + "\n";

        }
        return t;
    }

}
