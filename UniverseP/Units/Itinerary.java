package UniverseP.Units;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A member of a bus object
 * The class is a wrapper around a queue of all the pick up and drop of locations in the order they are visited
 */

public class Itinerary{
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

    public static Itinerary createEmptyItinerary(){
        return new Itinerary();
    }

    public boolean isEmpty(){
        return destinationQueue.isEmpty();
    }

    public ActionableLocation poll(){
        return destinationQueue.poll();
    }

    public ActionableLocation peek(){
        return destinationQueue.peek();
    }

    public boolean offer(ActionableLocation e){
        return destinationQueue.offer(e);
    }

    public Object[] toArray(){
        return destinationQueue.toArray();
    }


    @Override
    public String toString(){
        String output = " ---Itinerary: ";
        for(Object iterator : this.toArray()) {
            output += (ActionableLocation) iterator + " ";
        }
        return output;
    }

}
