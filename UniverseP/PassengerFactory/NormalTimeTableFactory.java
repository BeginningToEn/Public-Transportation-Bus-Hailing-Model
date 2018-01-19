package UniverseP.PassengerFactory;

import UniverseP.Passenger;
import UniverseP.ScenarioDefinition;

import java.util.*;

/**
 * Creates a PassengerTimeTable where passengers are expected to be normally distributed in spawn location
 * over the grid, normally distributed in destination location over the grid, and normally distributed
 * in spawnTurn over the scenario total life cycle
 */

class NormalTimeTableFactory{

    private Random randNumGen;
    private int numTurns;
    private int gridLength;
    private int gridHeight;

    private int numPassengers;
    private int x_spawnAvg;
    private int y_spawnAvg;
    private int spawnStandardDeviation;
    private int x_destinationAvg;
    private int y_destinationAvg;
    private int destinationStandardDeviation;
    private int spawnTimeAvg;
    private int spawnTimeStandardDeviation;


    NormalTimeTableFactory() {
        this.randNumGen = new Random();
    }

    private void updateDefinition(ScenarioDefinition myScenarioDef, PassengerDistributionDefinition myPassengerDef) {
        this.numTurns = myScenarioDef.getNumTurns();
        this.gridLength = myScenarioDef.getGridLength();
        this.gridHeight = myScenarioDef.getGridHeight();
        this.numPassengers = myPassengerDef.getNumPassengers();
        this.x_spawnAvg = myPassengerDef.getX_spawnAvg();
        this.y_spawnAvg = myPassengerDef.getY_spawnAvg();
        this.spawnStandardDeviation = myPassengerDef.getSpawnStandardDeviation();
        this.x_destinationAvg = myPassengerDef.getX_destinationAvg();
        this.y_destinationAvg = myPassengerDef.getY_destinationAvg();
        this.destinationStandardDeviation = myPassengerDef.getDestinationStandardDeviation();
        this.spawnTimeAvg = myPassengerDef.getSpawnTimeAvg();
        this.spawnTimeStandardDeviation = myPassengerDef.getSpawnTimeStandardDeviation();
    }


    PassengerTimeTable createDistribution( ScenarioDefinition myScenarioDef,
                                           PassengerDistributionDefinition myPassengerDef ) {

        this.updateDefinition(myScenarioDef, myPassengerDef);

        PassengerTimeTable passengerTimeTable  = new PassengerTimeTable();
        Passenger passengerIterator;
        int spawnTurnIterator;

        for ( int i = 0; i < numPassengers; i++ ) {

            passengerIterator = this.createNormalPassenger(i);
            spawnTurnIterator = passengerIterator.getSpawnTurn();

            if ( !passengerTimeTable .containsKey(spawnTurnIterator) ){
                passengerTimeTable .put(spawnTurnIterator, new ArrayList<>());
            }
            passengerTimeTable.get(spawnTurnIterator).add( passengerIterator );
        }

        return passengerTimeTable ;
    }

    private Passenger createNormalPassenger(int ID ) {


        int spawnTurn = this.generateSpawnTurn();

        //generates the spawn according to a normal distribution and makes sure it's inbounds
        int x_spawn;
        int y_spawn;
        do {
            x_spawn = (int) Math.round( randNumGen.nextGaussian() * spawnStandardDeviation + x_spawnAvg );
            y_spawn = (int) Math.round( randNumGen.nextGaussian() * spawnStandardDeviation + y_spawnAvg );
        } while (!this.isValidSpawn(x_spawn, y_spawn));

        //generates the destination according to a normal distribution and makes sure it's inbounds and != spawn
        //since spawn == destination would be a nonsensical trip
        int x_dest;
        int y_dest;
        do {
            x_dest = (int) Math.round( randNumGen.nextGaussian() * destinationStandardDeviation + x_destinationAvg );
            y_dest = (int) Math.round( randNumGen.nextGaussian() * destinationStandardDeviation + y_destinationAvg );
        } while (!this.isValidDestination(x_spawn, y_spawn, x_dest, y_dest));

        return new Passenger(ID, x_spawn, y_spawn, x_dest, y_dest, spawnTurn);
    }

    private int generateSpawnTurn() {

        int spawnTurn;

        //if spawnTimeAvg is out of bounds (spawnTimeAvg==numTurns is out of bounds due to 0-indexing )or if
        // spawnTimeAvg is 0 or if spawnTimeStandardDev is 0 we generate spawnTurn by normal distribution
        if ( spawnTimeAvg <= 0 || spawnTimeAvg >= numTurns ||spawnTimeStandardDeviation == 0 ) {
            spawnTurn = randNumGen.nextInt( numTurns );
        } else {
            do {
                spawnTurn = (int) Math.round( randNumGen.nextGaussian() * spawnTimeStandardDeviation + spawnTimeAvg );
            } while (!this.isValidSpawnTurn(spawnTurn));
        }

        return spawnTurn;
    }

    private boolean isValidSpawnTurn(int spawnTurn) {
        return spawnTurn >= 0 && spawnTurn < numTurns;
    }

    private boolean isValidSpawn(int x_coordinate, int y_coordinate) {
        if ( x_coordinate < 0 || y_coordinate < 0 ) { return false; }
        if ( x_coordinate >= gridLength ) { return false; }
        if ( y_coordinate >= gridHeight ) { return false; }
        return true;
    }

    private boolean isValidDestination( int x_spawn, int y_spawn, int x_destination, int y_destination) {
        return x_spawn != x_destination || y_spawn != y_destination;
    }
}