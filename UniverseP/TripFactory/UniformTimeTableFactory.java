package UniverseP.TripFactory;

import UniverseP.Units.Trip;
import UniverseP.ScenarioSimulation.ScenarioDefinition;

import java.util.*;

/**
 * Creates a PassengerTimeTable where passengers are expected to be uniformly distributed in spawn location
 * over the grid, uniformly distributed in destination location over the grid, and uniformly distributed
 * in spawnTurn over the scenario total life cycle
 */

class UniformTimeTableFactory{

    private Random randNumGen;
    private int numTurns;
    private int gridLength;
    private int gridHeight;
    private int numPassengers;

    UniformTimeTableFactory() {
        this.randNumGen = new Random();
    }

    private void updateDefinition(ScenarioDefinition myScenarioDef) {
        this.numTurns = myScenarioDef.getNumTurns();
        this.gridLength = myScenarioDef.getGridLength();
        this.gridHeight = myScenarioDef.getGridHeight();
        this.numPassengers = myScenarioDef.getNumTrips();
    }


    TripTimeTable createTrips(ScenarioDefinition myScenarioDef ) {

        this.updateDefinition(myScenarioDef);

        TripTimeTable tripTimeTable = new TripTimeTable();
        Trip tripIterator;
        int spawnTurnIterator;

        for ( int i = 0; i < numPassengers; i++ ) {

            tripIterator = this.createUniformTrip(i);
            spawnTurnIterator = tripIterator.getTimeRequested();

            if ( !tripTimeTable.containsKey(spawnTurnIterator) ){
                tripTimeTable.put(spawnTurnIterator, new ArrayList<>());
            }

            tripTimeTable.get(tripIterator.getTimeRequested()).add(tripIterator);
        }

        return tripTimeTable;
    }

    private Trip createUniformTrip(int ID ) {

        int spawnTurn = randNumGen.nextInt( numTurns );
        int x_spawn = randNumGen.nextInt( gridLength );
        int y_spawn = randNumGen.nextInt( gridHeight );

        int x_destination;
        int y_destination;
        do {
            x_destination = randNumGen.nextInt( gridLength );
            y_destination = randNumGen.nextInt( gridHeight );
        } while (!this.isValidDestination(x_spawn, y_spawn, x_destination, y_destination));

        return new Trip(ID, x_spawn, y_spawn, x_destination, y_destination, spawnTurn);
    }

    //destination may not be the same as spawn; spawn = destination is not a valid trip
    private boolean isValidDestination( int x_spawn, int y_spawn, int x_destination, int y_destination) {
        return x_spawn != x_destination || y_spawn != y_destination;
    }
}
