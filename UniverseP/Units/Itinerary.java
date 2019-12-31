package UniverseP.Units;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Set;
import java.util.HashSet;

/**
 * A member of a bus object
 * The class is a wrapper around a queue of all the pick up and drop of locations in the order they are visited
 */

public class Itinerary{
    private Queue<ActionableLocation> destinationQueue;
    private Set<Integer> tripIDs;

    public Itinerary() {
        this.destinationQueue = new ConcurrentLinkedDeque<>();
        this.tripIDs = new HashSet<>();
    }

    public boolean isEnroutePickUp() {
        return destinationQueue.peek().isPickUpLocation();
    }

    //Itinerary creation method used by the SinglePassengerStrat
    public static Itinerary createDirectItinerary(Trip myTrip) {
        Itinerary output = new Itinerary();
        output.addTripID(myTrip.getID());
        output.offer(myTrip.getSpawn());
        output.offer(myTrip.getDestination());
        return output;
    }

    public static Itinerary createEmptyItinerary(){
        return new Itinerary();
    }

    public boolean isEmpty(){
        return destinationQueue.isEmpty();
    }

    public void addTripID(int tripID){ tripIDs.add(tripID); }

    public Set<Integer> getTripIDs() {
        return tripIDs;
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
        String output = "";
        for(Object iterator : this.toArray()) {
            output += (ActionableLocation) iterator + " ";
        }
        return output;
    }

}
