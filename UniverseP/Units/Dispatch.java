package UniverseP.Units; /**
 * Dispatch class tells buses where to go to pick up passengers
 * Assigns passengers based on different strategies
 */

import Strategies.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.ScenarioComponents.ScenarioDefinition;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Dispatch {
    //needs to maintain an up to date available buses list for UberStrategy;

    private Strategy myStrategy;
    private Map<Integer, Bus> allBuses;
    private Set<Integer> availableBusIDs;
    private Set<Integer> assignedBuses;
    private Map<Integer, Passenger> allPassengers;
    private Queue<Passenger> passengerQueue;
    private int busCapacity;

    public Dispatch(Map<Integer, Bus> allBuses, Queue<Passenger> passengerQueue, int busCapacity){
        this.myStrategy = new SinglePassengerStrategy(allBuses, availableBusIDs, passengerQueue);
        this.allBuses = allBuses;
        this.availableBusIDs = allBuses.keySet();
        this.assignedBuses = new HashSet<>();
        this.passengerQueue = new ConcurrentLinkedQueue();
        this.busCapacity = busCapacity;
    }

    public void moveBuses(){
        for (Integer myIterator : assignedBuses){
            allBuses.get(myIterator).move();
        }
    }

    public void checkIfArrived(){

    }

    //use assignPassengers function when passengerQueue is not empty
    public void assignPassengers(){
        while ( !passengerQueue.isEmpty() && !availableBusIDs.isEmpty()){
            myStrategy.assignBuses();
        }
    }

    public void updateAvailBuses() {

    }

}
