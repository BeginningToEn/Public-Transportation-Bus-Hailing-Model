package UniverseP.Units; /**
 * Dispatch class tells buses where to go to pick up passengers
 * Assigns passengers based on different strategies
 */

import Strategies.Strategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Dispatch {
    //needs to maintain an up to date available buses list for UberStrategy;

    private Strategy myStrategy;
    private Map<Integer, Bus> allBuses;
    private Set<Integer> availableBuses;
    private Set<Integer> assignedBuses;
    private Map<Integer, Passenger> allPassengers;
    private Queue<Passenger> passengerQueue;

    public Dispatch(Strategy myStrategy, Map<Integer,Bus> allBuses, Queue<Passenger> passengerQueue){
        this.myStrategy = myStrategy;
        this.allBuses = allBuses;
        this.availableBuses = new HashSet<Integer>();
        this.assignedBuses = new HashSet<Integer>();
        this.passengerQueue = new ConcurrentLinkedQueue();
    }

    public void moveBuses(){
        for (Integer myIterator : assignedBuses){
            allBuses.get(myIterator).move();
        }
    }

    //use assignPassengers function when passengerQueue is not empty
    public void assignPassengers(){
        while ( !passengerQueue.isEmpty() && !availableBuses.isEmpty()){
            myStrategy.assignBuses();
        }
    }

    public Passenger getPassenger(int ID) {
        return allPassengers.get(ID);
    }

}
