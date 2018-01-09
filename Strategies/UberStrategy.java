package Strategies;

import UniverseP.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

//Uber Strategy- buses only carry one person
//If a bus is en route to pick up passengers and a bus that is closer becomes available assignments should change
public class UberStrategy implements Strategy{
    private Map<Integer, Bus> allBuses;
    private Queue<Passenger> passengerQueue;

    public UberStrategy(Map<Integer, Bus> allBuses, Queue<Passenger> passengerQueue){
        this.allBuses = allBuses;
        this.passengerQueue = passengerQueue;
    }

    //returns the ID of the bus closest that carries no passengers
    //we return busID instead of buses to make it easier to deal case where no bus is available
    //if no bus is available returns -1
    public int getClosestBusID(Passenger myPassenger){

        Location passengerSpawn = myPassenger.getSpawn();
        int busID = -1;
        int smallestDistance = Integer.MAX_VALUE;
        Bus iteratorBus;
        int distance;

        for (Integer iteratorKey : allBuses.keySet()){

            //in this strategy buses only carry one person max
            iteratorBus = allBuses.get(iteratorKey);
            if ( iteratorBus.hasPassenger()) { continue; };

            distance = ScenarioDefinition.getDistance(passengerSpawn, iteratorBus.getLocation());
            if ( distance < smallestDistance ) {
                smallestDistance = distance;
                busID = iteratorKey;
            }
        }

        return busID;
    }

    public void assignBuses(){

        Iterator<Passenger> passengerIterator = passengerQueue.iterator();
        int closestBusID;
        Passenger myPassenger;

        while ( passengerIterator.hasNext() ) {
            myPassenger = passengerIterator.next();
            closestBusID = getClosestBusID( myPassenger );
            Itinerary myItinerary = Itinerary.createDirectItinerary(myPassenger);
            allBuses.get(closestBusID).setItinerary(myItinerary);
        }
    }

    public void execute(){

    }

}
