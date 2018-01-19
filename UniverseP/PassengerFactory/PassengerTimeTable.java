package UniverseP.PassengerFactory;

import UniverseP.Passenger;

import java.util.HashMap;
import java.util.List;

/**
 * Wrapper class around hashmap that lists all passenger objects at a given spawnTurn
 * so we can have a print function for testing and so we can implement the interface
 * PassengerSource so that we can write the program to be able to handle different types of passenger sources be it
 * from a pre-generated table or from live user input
 */

public class PassengerTimeTable extends HashMap<Integer, List<Passenger>> {

    public void printAllPassengers() {
        for ( int iterator : this.keySet() ) {
            for ( Passenger passIt : this.get(iterator) ) {
                System.out.println(passIt);
            }
        }
    }
}
