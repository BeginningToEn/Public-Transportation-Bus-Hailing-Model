package Strategies;

import UniverseP.ScenarioComponents.ScenarioDefinition;
import UniverseP.Units.Bus;
import UniverseP.Units.Itinerary;
import UniverseP.Units.Passenger;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;

//Uber Strategy- buses only carry one person
//If a bus is en route to pick up passengers and a bus that is closer becomes available assignments should change
public class SinglePassengerStrategy implements Strategy{
    private Map<Integer, Bus> availableBuses;
    private Queue<Passenger> passengerQueue;

    public SinglePassengerStrategy(Map<Integer, Bus> allBuses, Queue<Passenger> passengerQueue){
        this.availableBuses = allBuses;
        this.passengerQueue = passengerQueue;
    }

    //returns the ID of the bus closest that carries no passengers
    //we return busID instead of buses to make it easier to deal case where no bus is available
    //if no bus is available  we return an empty optional
    private Optional<Integer> getClosestAvailableBusID(Passenger myPassenger){

        Optional<Integer> busID = Optional.empty();
        int smallestDistance = Integer.MAX_VALUE;
        Bus iteratorBus;
        int distance;

        for (Integer iteratorKey : availableBuses.keySet()){

            iteratorBus = availableBuses.get(iteratorKey);

            distance = ScenarioDefinition.getDistance(myPassenger.getSpawn(), iteratorBus.getLocation());
            if ( distance < smallestDistance ) {
                smallestDistance = distance;
                busID = Optional.of(iteratorKey);
            }
        }

        return busID;
    }

    private void assignItinerary(int BusID, Passenger myPassenger) {
        Itinerary myItinerary = Itinerary.createDirectItinerary(myPassenger);
        availableBuses.get(BusID).setMyItinerary(myItinerary);
    }

    public void assignBuses(){

        while ( !passengerQueue.isEmpty() ) {

            if ( availableBuses.isEmpty() ) {   //guard clause
                return;
            }

            Passenger myPassenger = passengerQueue.poll();
            int closestBusID = getClosestAvailableBusID(myPassenger).get();
            this.assignItinerary( closestBusID, myPassenger );
            availableBuses.remove(closestBusID);
        }
    }

}
