package UniverseP;

import java.util.*;

//Creates PassengerTimeTables with pre-defined numTurns and grid dimensions

public class PassengerTimeTableFactory {
    private Random randNumGen;
    private int numTurns;
    private int gridLength;
    private int gridHeight;
    private PassengerDistributionDefinition myDef;

    public PassengerTimeTableFactory(ScenarioDefinition myDefinition) {
        this.randNumGen = new Random();
        this.numTurns = myDefinition.getNumTurns();
        this.gridLength = myDefinition.getGridLength();
        this.gridHeight = myDefinition.getGridHeight();
    }

    public Map<Integer,List<Passenger>> createDistribution() {
        Map<Integer,List<Passenger>> output = new HashMap<>();
        int howManyPassengers = myDef.getNumPassengers();

        

        for ( int i = 0; i < howManyPassengers; i++ ) {

            if ( !output.containsKey(i) ){
                output.put(i, new ArrayList<Passenger>());
            }
            output.get(i).add( this.createUniformPassenger(i));
        }

        return output;
    }

    public Map<Integer,List<Passenger>> createUniformDistribution( int numPassengers ) {
        Map<Integer,List<Passenger>> output = new HashMap<>();

        for ( int i = 0; i < numPassengers; i++ ) {

            if ( !output.containsKey(i) ){
                output.put(i, new ArrayList<Passenger>());
            }
            output.get(i).add( this.createUniformPassenger(i));
        }

        return output;
    }

    public Map<Integer,List<Passenger>> createNormalDistribution( int numPassengers, PassengerDistributionDefinition myDef ) {
        this.myDef = myDef;
        Map<Integer,List<Passenger>> output = new HashMap<>();

        //CODE HERE

        return output;
    }

    private Passenger createUniformPassenger(int ID ) {

        int spawnTurn = randNumGen.nextInt( numTurns);
        int x_spawn = randNumGen.nextInt( gridLength );
        int y_spawn = randNumGen.nextInt( gridHeight );

        int x_destination;
        int y_destination;
        do {
            x_destination = randNumGen.nextInt( gridLength );
            y_destination = randNumGen.nextInt( gridHeight );
        } while (this.isValidDestination(x_spawn, y_spawn, x_destination, y_destination));

        return new Passenger(ID, x_spawn, y_spawn, x_destination, y_destination, spawnTurn);
    }

    private Passenger createNormalPassenger(int ID ) {

        int spawnTurn = (int) randNumGen.nextGaussian() * ( numTurns * 3 / 2 ) + ( numTurns / 2 );
        int x_spawn = (int) randNumGen.nextGaussian() * ( gridLength * 3 / 2 ) + ( gridLength / 2 );
        int y_spawn = (int) randNumGen.nextGaussian(myDefinition.getGridHeight() );
        int x_destination = (int) randNumGen.nextGaussian(myDefinition.getGridLength() );
        int y_destination = (int) randNumGen.nextGaussian(myDefinition.getGridHeight() );

        return new Passenger(ID, x_spawn, y_spawn, x_destination, y_destination, spawnTurn);

        //need to enforce that spawn and destination cannot be the same for a passenger
        //need to encode that for normal dist the mean spawn should be different from the mean destination
        //need to move uniform vs normal distribution to ScenarioDefinition and include mean and std there too
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
        if ( x_spawn != x_destination || y_spawn != y_destination ) {
            return this.isValidSpawn(x_spawn, y_spawn);
        }
        return false;
    }
}
