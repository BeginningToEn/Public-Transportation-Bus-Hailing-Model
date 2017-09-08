import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public class Itinerary {
    private Queue<PassengerActionLocation> destinationQueue;

    public Itinerary() {
        this.destinationQueue = new ConcurrentLinkedDeque<>();
    }

    public void offer (PassengerActionLocation myLocation) {
        destinationQueue.offer(myLocation);
    }
}
