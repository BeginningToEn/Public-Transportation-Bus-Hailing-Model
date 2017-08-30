import java.awt.Point;

public class UniverseDefinition {
    private int customNumBuses;
    private int customNumPassengers;
    private int[] numBuses;
    private int[] numPassengers;
    private int gridLength;
    private int gridHeight;
    private int numSimulations;
    public final int largestDistancePossible;

    public UniverseDefinition(int customNumBuses, int customNumPassengers, int numSimulations){
        this.customNumBuses = customNumBuses;
        this.customNumPassengers = customNumPassengers;
        this.numBuses = new int[]{1, 25, 50, 75, 100, 200};
        this.numPassengers = new int[]{100, 500, 1000};
        this.gridLength = 100;                                //this gives the grid 1000 spaces should give good density
        this.gridHeight = 100;                                //if there is 100 buses that's 1/10 of spaces with a bus
        this.numSimulations = numSimulations;
        this.largestDistancePossible = gridLength + gridHeight;
    }

    public static int getDistance(Location a, Location b){
        return Math.abs( a.getX() - b.getX() ) + Math.abs( a.getY() - b.getY() );
    }
}
