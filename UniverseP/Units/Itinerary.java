package UniverseP.Units;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A member of a bus object
 * The class is a wrapper around a queue of all the pick up and drop of locations in the order they are visited
 */

public class Itinerary extends ConcurrentLinkedDeque<ActionableLocation>{
    private Queue<ActionableLocation> destinationQueue;

    public Itinerary() {
        this.destinationQueue = new ConcurrentLinkedDeque<>();
    }

    public boolean isEnroutePickUp() {
        return destinationQueue.peek().isPickUpLocation();
    }

    //Itinerary creation method used by the SinglePassengerStrat
    public static Itinerary createDirectItinerary(Passenger myPassenger) {
        Itinerary output = new Itinerary();
        output.offer(myPassenger.getSpawn());
        output.offer(myPassenger.getDestination());
        return output;
    }

    @Override
    public String toString(){
        String output = "";
        for(Object iterator : this.toArray()) {
            output += (ActionableLocation) iterator + " ";
        }
        return output;
    }
}
