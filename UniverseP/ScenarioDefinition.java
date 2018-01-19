package UniverseP;

import UniverseP.Location;
import UniverseP.PassengerFactory.PassengerTimeTable;

public class ScenarioDefinition {

    private int numBuses;
    private int gridLength;
    private int gridHeight;
    private int numTurns;


    public ScenarioDefinition(int numBuses, int gridLength, int gridHeight, int numTurns){
        this.numBuses = numBuses;       //1, 25, 50, 75, 100, 200
        this.gridLength = gridLength;                                //this gives the grid 1000 spaces should give good density
        this.gridHeight = gridHeight;                                //if there is 100 buses that's 1/10 of spaces with a bus
        this.numTurns = numTurns;
    }

    public static int getDistance(Location a, Location b){
        return Math.abs( a.getX() - b.getX() ) + Math.abs( a.getY() - b.getY() );
    }

    public static int getDistance(ActionableLocation a, Location b){
        return Math.abs( a.getX() - b.getX() ) + Math.abs( a.getY() - b.getY() );
    }

    //Getters
    public int getGridLength() {
        return gridLength;
    }
    public int getGridHeight() {
        return gridHeight;
    }
    public int getNumTurns() {
        return numTurns;
    }
}
