package Strategies.TwoPoolStrategy;

import UniverseP.Units.Trip;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by EG OLIVER RC on 12/8/2019.
 */
public class TwoPoolTripCoordinator {


    Set<Trip> newTrips;
    LinkedList<Trip> unpairedTrips;
    private Queue<Trip[]> pickupQueue;
    int currentTurn;
    int maxWait = 10;

    public TwoPoolTripCoordinator(){
        this.newTrips = new HashSet<>();
        this.unpairedTrips = new LinkedList<>();
        this.pickupQueue = new ConcurrentLinkedDeque<>();
        this.currentTurn = 0;
    }

    public void handleNewTurn(Iterable<Trip>  newTrips){

        for(Trip myTrip : newTrips){
            Optional<Trip> validPair = getAndRemovePair(myTrip);
            if(validPair.isPresent()){
                pickupQueue.add(new Trip[]{myTrip, validPair.get()});
            } else {
                unpairedTrips.add(myTrip);
            }
        }

        while(!unpairedTrips.isEmpty() && exceedsMaxWait(unpairedTrips.peekFirst())){
            pickupQueue.add(new Trip[]{unpairedTrips.pollFirst()});
        }
    }

    private Optional<Trip> getAndRemovePair(Trip myTrip){
        Iterator<Trip> iter = unpairedTrips.iterator();

        while(iter.hasNext()){
            Trip potentialPair = iter.next();
            if(ComboValidator.isValidCombo(myTrip, potentialPair)){
                iter.remove();
                return Optional.of(potentialPair);
            }
        }

        return Optional.empty();
    }

    private boolean exceedsMaxWait(Trip unpairedTrip){
        return unpairedTrip.getTimeRequested() + maxWait > currentTurn;
    }

    private void addTrips(Iterable<Trip>  newTrips){
        for(Trip myTrip : newTrips){
            this.addTrip(myTrip);
        }
    }

    public void addTrip(Trip newTrip){
        //pickupQueue.offer(newTrip);
    }

    public void updateTime(int turn){
        this.currentTurn = turn;
    }

    public boolean isEmpty(){
        return pickupQueue.isEmpty();
    }

    public Trip[] poll(){
        return pickupQueue.poll();
    }
}