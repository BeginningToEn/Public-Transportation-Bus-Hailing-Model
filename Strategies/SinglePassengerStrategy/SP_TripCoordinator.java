package Strategies.SinglePassengerStrategy;

import UniverseP.Units.Trip;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by EG OLIVER RC on 11/4/2019.
 */

//Trip coordinator for Single Passenger Strategy
//Simple queue

public class SP_TripCoordinator {

    private Queue<Trip> pickupQueue;

    public SP_TripCoordinator(){
        this.pickupQueue = new ConcurrentLinkedDeque<>();
    }

    public void addTrip(Iterable<Trip>  newTrips){
        for(Trip myTrip : newTrips){
            pickupQueue.offer(myTrip);
        }
    }

    public void addTrip(Trip newTrip){
        pickupQueue.offer(newTrip);
    }

    public boolean isEmpty(){
        return pickupQueue.isEmpty();
    }

    public Trip poll(){
        return pickupQueue.poll();
    }
}
