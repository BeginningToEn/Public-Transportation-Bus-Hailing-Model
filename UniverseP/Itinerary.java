package UniverseP;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public class Itinerary extends ConcurrentLinkedDeque<PassengerActionLocation>{
    private Queue<PassengerActionLocation> destinationQueue;

    public Itinerary() {
        this.destinationQueue = new ConcurrentLinkedDeque<>();
    }

    @Override
    public boolean offer (PassengerActionLocation myLocation) {
        destinationQueue.offer(myLocation);
        return this.isEmpty();
    }

    public boolean isEnroutePickUp() {
        return destinationQueue.peek().isPickUpLocation();
    }

    public static Itinerary createDirectItinerary(Passenger myPassenger) {
        Itinerary output = new Itinerary();
        output.offer(myPassenger.getSpawn());
        output.offer(myPassenger.getDestination());
        return output;
    }
}
