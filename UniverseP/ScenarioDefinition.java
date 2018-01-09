package UniverseP;

import UniverseP.Location;

public class ScenarioDefinition {
    private int numBuses;
    private int numPassengers;
    private int gridLength;
    private int gridHeight;
    private int numTurns;
    private int numSimulations;

    public final int largestDistancePossible;

    public ScenarioDefinition(int numBuses, int numPassengers, int numTurns, int numSimulations){
        this.numBuses = numBuses;       //1, 25, 50, 75, 100, 200
        this.numPassengers = numPassengers;     //100, 500, 1000
        this.gridLength = 100;                                //this gives the grid 1000 spaces should give good density
        this.gridHeight = 100;                                //if there is 100 buses that's 1/10 of spaces with a bus
        this.numTurns = numTurns;
        this.numSimulations = numSimulations;
        this.largestDistancePossible = gridLength + gridHeight;
    }

    public static int getDistance(Location a, Location b){
        return Math.abs( a.getX() - b.getX() ) + Math.abs( a.getY() - b.getY() );
    }

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
