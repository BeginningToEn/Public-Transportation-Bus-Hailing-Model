package Strategies;

import UniverseP.ScenarioSimulation.ScenarioDefinition;
import UniverseP.Units.Bus;
import UniverseP.ScenarioSimulation.BusCoordinator;
import UniverseP.Units.Itinerary;
import UniverseP.Units.Trip;

import java.util.*;

//Uber Strategy- buses only carry one person
//If a bus is en route to pick up passengers and a bus that is closer becomes available assignments should change
public class SinglePassengerStrategy implements Strategy{
    private Map<Integer, Bus> allBuses;
    private BusCoordinator myCoordinator;
    private Queue<Trip> pickupQueue;

    public SinglePassengerStrategy(Map<Integer, Bus> allBuses, BusCoordinator myCoordinator,
                                   Queue<Trip> pickupQueue){
        this.allBuses = allBuses;
        this.myCoordinator = myCoordinator;
        this.pickupQueue = pickupQueue;
    }

    //returns the ID of the bus closest that carries no passengers
    //we return busID instead of buses to make it easier to deal case where no bus is available
    //if no bus is available  we return an empty optional
    private Optional<Integer> getClosestAvailableBusID(Trip myTrip){

        Optional<Integer> closestID = Optional.empty();
        int smallestDistance = Integer.MAX_VALUE;

        int nextID;
        Iterator<Integer> nextIdIterator = myCoordinator.getAvailable().iterator();

        while ( nextIdIterator.hasNext() ) {

            nextID = nextIdIterator.next();
            Bus iteratorBus = allBuses.get(nextID);

            int distance = ScenarioDefinition.getDistance(myTrip.getSpawn(), iteratorBus.getLocation());
            if ( distance < smallestDistance ) {
                smallestDistance = distance;
                closestID = Optional.of(nextID);
            }
        }

        return closestID;
    }

    private void assignItinerary(int BusID, Trip myTrip) {
        Itinerary myItinerary = Itinerary.createDirectItinerary(myTrip);
        allBuses.get(BusID).setItinerary(myItinerary);
    }

    public void assignBuses(){

        while ( !pickupQueue.isEmpty() && myCoordinator.busAvailable() ) {

            Trip myTrip = pickupQueue.poll();
            int closestBusID = getClosestAvailableBusID(myTrip).get();
            this.assignItinerary( closestBusID, myTrip);
            myCoordinator.recordAssignments(closestBusID);
        }
    }

    public Map<Integer,Trip> createAssignments(){
        Map<Integer,Trip> myAssignments = new HashMap<>();

        while ( !pickupQueue.isEmpty() && myCoordinator.busAvailable() ) {

            Trip myTrip = pickupQueue.poll();
            int closestBusID = getClosestAvailableBusID(myTrip).get();
            myAssignments.put( closestBusID, myTrip );
            myCoordinator.recordAssignments(closestBusID);
        }

        return myAssignments;
    }

}
