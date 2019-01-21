package UniverseP.PassengerFactory;

import UniverseP.Units.Passenger;
import UniverseP.ScenarioSimulation.ScenarioDefinition;

import java.util.HashMap;
import java.util.List;

/**
 * Wrapper class around hashmap that lists all passenger objects at a given spawnTurn
 * This is not a list of all passengers, it is a map between specific points in time and lists of passengers that spawn
 * at that specific time
 * so we can have a print function for testing and so we can implement the interface
 * PassengerSource so that we can write the program to be able to handle different types of passenger sources be it
 * from a pre-generated table or from live user input
 */

public class PassengerTimeTable extends HashMap<Integer, List<Passenger>>{

    /*
     * For validation. Since ScenarioSimulator can draw data from PassengerTimeTable we need to validate the
     * PassengerTimeTable's PassDistDef against the ScenarioDefinition of the simulation to make sure none of the
     * passengers are spawned out of bounds
     */
    private ScenarioDefinition myDef;

    public PassengerTimeTable () {
        this.myDef = myDef;
    }

    public ScenarioDefinition getMyDef() {
        return myDef;
    }

    public void printAllPassengers() {
        for ( int iterator : this.keySet() ) {
            for ( Passenger passIt : this.get(iterator) ) {
                System.out.println(passIt);
            }
        }
    }

    public String toStringAllXSpawn() {
        String output = "";
        for ( int iterator : this.keySet() ) {
            for ( Passenger passIt : this.get(iterator) ) {
                output += passIt.getSpawn().getX() + "\n";
            }
        }
        return output;
    }

    public int howManyPassengers() {
        int numPassengers = 0;
        for ( int iterator : this.keySet() ) {
            numPassengers += this.get(iterator).size();
        }
        return numPassengers;
    }

    public int[] testAllXSpawn() {

        int[] allXSPawns = new int[this.howManyPassengers()];

        int i = 0;

        for ( int iterator : this.keySet() ) {
            for ( Passenger passIt : this.get(iterator) ) {
                allXSPawns[i] = passIt.getSpawn().getX();
            }
        }

        return allXSPawns;
    }

}
