package UniverseP; /**
 * Dispatch class tells buses where to go to pick up passengers
 * Assigns passengers based on different strategies
 */

import Strategies.Strategy;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Dispatch {
    //needs to maintain an up to date available buses list for UberStrategy;

    private Strategy myStrategy;
    private Map<Integer, Bus> availableBuses;
    private Map<Integer, Bus> allBuses;
    private Map<Integer, Passenger> allPassengers;
    private Queue<Passenger> passengerQueue;

    public Dispatch(){

        this.passengerQueue = new ConcurrentLinkedQueue();
    }

    public void moveBuses(){
        for (Bus myIterator : allBuses.values()){
            myIterator.move();
        }
    }

    //use assignPassengers function when passengerQueue is not empty
    public void assignPassengers(){
        while ( !passengerQueue.isEmpty() && !availableBuses.isEmpty()){
            myStrategy.execute();

        }
    }

    public Passenger getPassenger(int ID) {
        return allPassengers.get(ID);
    }

}
