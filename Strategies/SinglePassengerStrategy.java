package Strategies;

import UniverseP.ScenarioComponents.ScenarioDefinition;
import UniverseP.Units.Bus;
import UniverseP.Units.BusCoordinator;
import UniverseP.Units.Itinerary;
import UniverseP.Units.Passenger;

import java.util.*;

//Uber Strategy- buses only carry one person
//If a bus is en route to pick up passengers and a bus that is closer becomes available assignments should change
public class SinglePassengerStrategy implements Strategy{
    private Map<Integer, Bus> allBuses;
    private BusCoordinator myCoordinator;
    private Queue<Passenger> passengerQueue;

    public SinglePassengerStrategy(Map<Integer, Bus> allBuses, BusCoordinator myCoordinator,
                                   Queue<Passenger> passengerQueue){
        this.allBuses = allBuses;
        this.myCoordinator = myCoordinator;
        this.passengerQueue = passengerQueue;
    }

    //returns the ID of the bus closest that carries no passengers
    //we return busID instead of buses to make it easier to deal case where no bus is available
    //if no bus is available  we return an empty optional
    private Optional<Integer> getClosestAvailableBusID(Passenger myPassenger){

        Optional<Integer> closestID = Optional.empty();
        int smallestDistance = Integer.MAX_VALUE;

        int nextID;
        Iterator<Integer> nextIdIterator = myCoordinator.getAvailable().iterator();

        while ( nextIdIterator.hasNext() ) {

            nextID = nextIdIterator.next();
            Bus iteratorBus = allBuses.get(nextID);

            int distance = ScenarioDefinition.getDistance(myPassenger.getSpawn(), iteratorBus.getLocation());
            if ( distance < smallestDistance ) {
                smallestDistance = distance;
                closestID = Optional.of(nextID);
            }
        }

        return closestID;
    }

    private void assignItinerary(int BusID, Passenger myPassenger) {
        Itinerary myItinerary = Itinerary.createDirectItinerary(myPassenger);
        allBuses.get(BusID).setMyItinerary(myItinerary);
    }

    public void assignBuses(){

        while ( !passengerQueue.isEmpty() && myCoordinator.busAvailable() ) {

            Passenger myPassenger = passengerQueue.poll();
            int closestBusID = getClosestAvailableBusID(myPassenger).get();
            this.assignItinerary( closestBusID, myPassenger );
            myCoordinator.recordAssignments(closestBusID);
        }
    }

}
