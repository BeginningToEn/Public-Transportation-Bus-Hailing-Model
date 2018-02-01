package UniverseP;

import UniverseP.PassengerFactory.NormalDistributionDefinition;
import java.util.Optional;

public class ScenarioDefinition {

    private int gridLength;
    private int gridHeight;

    private int numBuses;
    private int numTurns;

    private int numPassengers;


    public ScenarioDefinition(int gridLength, int gridHeight, int numPassengers, int numBuses, int numTurns){

        this.gridLength = gridLength;   //this gives the grid 1000 spaces should give good density
        this.gridHeight = gridHeight;   //if there is 100 buses that's 1/10 of spaces with a bus
        this.numBuses = numBuses;                //1, 25, 50, 75, 100, 200
        this.numTurns = numTurns;
        this.numPassengers = numPassengers;
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
    public int getNumPassengers() { return numPassengers; }
}
