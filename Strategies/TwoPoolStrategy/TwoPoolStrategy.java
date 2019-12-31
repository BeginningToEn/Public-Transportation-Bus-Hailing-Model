package Strategies.TwoPoolStrategy;

import Strategies.Strategy;
import UniverseP.ScenarioSimulation.BusCoordinator;
import UniverseP.ScenarioSimulation.ScenarioDefinition;
import UniverseP.Units.*;

import java.util.*;

//Uber Strategy- buses only carry one person
//If a bus is en route to pick up passengers and a bus that is closer becomes available assignments should change
public class TwoPoolStrategy implements Strategy {
    private Map<Integer, Bus> allBuses;
    private BusCoordinator myCoordinator;
    private TwoPoolTripCoordinator myTripCoordinator;

    public TwoPoolStrategy(Map<Integer, Bus> allBuses, BusCoordinator myCoordinator){
        this.allBuses = allBuses;
        this.myCoordinator = myCoordinator;
        myTripCoordinator = new TwoPoolTripCoordinator();
    }

    //returns the ID of the bus closest that carries no passengers
    //we return busID instead of buses to make it easier to deal case where no bus is available
    //if no bus is available  we return an empty optional
    private Optional<Integer> getClosestAvailableBusID(Trip myTrip){

        Optional<Integer> closestID = Optional.empty();
        int smallestDistance = Integer.MAX_VALUE;

        for ( int busID : myCoordinator.getAvailable() ) {

            Bus myBus = allBuses.get(busID);

            int distance = ScenarioDefinition.getDistance(myTrip.getSpawn(), myBus.getLocation());
            if ( distance < smallestDistance ) {
                smallestDistance = distance;
                closestID = Optional.of(busID);
            }
        }

        return closestID;
    }

    private Optional<Assignment> getBestAssignment(Trip[] oneTwoTrip){

        if(oneTwoTrip.length == 1){
            return getBestSoloAssignment(oneTwoTrip[0]);
        } else if(oneTwoTrip.length == 2){
            return getBestDuoAssignment(oneTwoTrip);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Assignment> getBestSoloAssignment(Trip onePoolTrip){
        Optional<Integer> busID = getClosestAvailableBusID(onePoolTrip);

        if(busID.isPresent()){
            return Optional.of(new Assignment(Itinerary.createDirectItinerary(onePoolTrip), busID.get()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Assignment> getBestDuoAssignment(Trip[] twoPoolTrip){

        List<Assignment> allPossible = getAllPossibleItineraries(myCoordinator.getAvailable(), twoPoolTrip);

        if(allPossible.isEmpty()){
            return Optional.empty();
        } else {
            return allPossible.stream().min(Comparator.comparing(Assignment::getDistance));
        }
    }

    private List<Assignment> getAllPossibleItineraries(Set<Integer> availableBusesID, Trip[] twoPoolTrip){

        List<Assignment> allPossible = new ArrayList<>();

        for(int busID : availableBusesID){
            allPossible.add(get1212Assignment(busID, twoPoolTrip));
            allPossible.add(get1221Assignment(busID, twoPoolTrip));
            allPossible.add(get2121Assignment(busID, twoPoolTrip));
            allPossible.add(get2112Assignment(busID, twoPoolTrip));
        }

        return allPossible;
    }

    private Assignment get1212Assignment(int busID, Trip[] twoPoolTrip){

        Itinerary myItinerary = new Itinerary();
        myItinerary.addTripID(twoPoolTrip[0].getID());
        myItinerary.addTripID(twoPoolTrip[1].getID());
        myItinerary.offer(twoPoolTrip[0].getSpawn());
        myItinerary.offer(twoPoolTrip[1].getSpawn());
        myItinerary.offer(twoPoolTrip[0].getDestination());
        myItinerary.offer(twoPoolTrip[1].getDestination());

        int distance = Location.getDistance(allBuses.get(busID).getLocation(), twoPoolTrip[0].getSpawn());
        distance += Location.getDistance(twoPoolTrip[0].getSpawn(), twoPoolTrip[1].getSpawn());
        distance += Location.getDistance(twoPoolTrip[1].getSpawn(), twoPoolTrip[0].getDestination());
        distance += Location.getDistance(twoPoolTrip[0].getDestination(), twoPoolTrip[1].getDestination());

        return new Assignment(myItinerary, distance, busID);
    }

    private Assignment get1221Assignment(int busID, Trip[] twoPoolTrip){

        Itinerary myItinerary = new Itinerary();
        myItinerary.addTripID(twoPoolTrip[0].getID());
        myItinerary.addTripID(twoPoolTrip[1].getID());
        myItinerary.offer(twoPoolTrip[0].getSpawn());
        myItinerary.offer(twoPoolTrip[1].getSpawn());
        myItinerary.offer(twoPoolTrip[1].getDestination());
        myItinerary.offer(twoPoolTrip[0].getDestination());

        int distance = Location.getDistance(allBuses.get(busID).getLocation(), twoPoolTrip[0].getSpawn());
        distance += Location.getDistance(twoPoolTrip[0].getSpawn(), twoPoolTrip[1].getSpawn());
        distance += Location.getDistance(twoPoolTrip[1].getSpawn(), twoPoolTrip[1].getDestination());
        distance += Location.getDistance(twoPoolTrip[1].getDestination(), twoPoolTrip[0].getDestination());

        return new Assignment(myItinerary, distance, busID);
    }

    private Assignment get2121Assignment(int busID, Trip[] twoPoolTrip){

        Itinerary myItinerary = new Itinerary();
        myItinerary.addTripID(twoPoolTrip[0].getID());
        myItinerary.addTripID(twoPoolTrip[1].getID());
        myItinerary.offer(twoPoolTrip[1].getSpawn());
        myItinerary.offer(twoPoolTrip[0].getSpawn());
        myItinerary.offer(twoPoolTrip[1].getDestination());
        myItinerary.offer(twoPoolTrip[0].getDestination());

        int distance = Location.getDistance(allBuses.get(busID).getLocation(), twoPoolTrip[1].getSpawn());
        distance += Location.getDistance(twoPoolTrip[1].getSpawn(), twoPoolTrip[0].getSpawn());
        distance += Location.getDistance(twoPoolTrip[0].getSpawn(), twoPoolTrip[1].getDestination());
        distance += Location.getDistance(twoPoolTrip[1].getDestination(), twoPoolTrip[0].getDestination());

        return new Assignment(myItinerary, distance, busID);
    }

    private Assignment get2112Assignment(int busID, Trip[] twoPoolTrip){

        Itinerary myItinerary = new Itinerary();
        myItinerary.addTripID(twoPoolTrip[0].getID());
        myItinerary.addTripID(twoPoolTrip[1].getID());
        myItinerary.offer(twoPoolTrip[1].getSpawn());
        myItinerary.offer(twoPoolTrip[0].getSpawn());
        myItinerary.offer(twoPoolTrip[0].getDestination());
        myItinerary.offer(twoPoolTrip[1].getDestination());

        int distance = Location.getDistance(allBuses.get(busID).getLocation(), twoPoolTrip[1].getSpawn());
        distance += Location.getDistance(twoPoolTrip[1].getSpawn(), twoPoolTrip[0].getSpawn());
        distance += Location.getDistance(twoPoolTrip[0].getSpawn(), twoPoolTrip[0].getDestination());
        distance += Location.getDistance(twoPoolTrip[0].getDestination(), twoPoolTrip[1].getDestination());

        return new Assignment(myItinerary, distance, busID);
    }

    public List<Assignment> createAssignments(){
        List<Assignment> myAssignments = new ArrayList<>();

        while ( !myTripCoordinator.isEmpty() && myCoordinator.busAvailable() ) {

            Trip[] oneTwoTrip = myTripCoordinator.poll();
            Optional<Assignment> myAssignment = getBestAssignment(oneTwoTrip);

            if(myAssignment.isPresent()){
                myAssignments.add(myAssignment.get());
                myCoordinator.recordAssignments(myAssignment.get().getBusID());
            }
        }

        return myAssignments;
    }


    public Map<Integer,Itinerary> createItinerariesMap() {

        Map<Integer,Itinerary> myItinerariesMap = new HashMap<>();

        while ( !myTripCoordinator.isEmpty() && myCoordinator.busAvailable() ) {

            Trip[] oneTwoTrip = myTripCoordinator.poll();
            Optional<Assignment> myEntryOpt = getBestAssignment(oneTwoTrip);

            if(myEntryOpt.isPresent()){
                Assignment myEntry = myEntryOpt.get();
                int busID = myEntry.getBusID();
                Itinerary myItinerary = myEntry.getItinerary();
                myItinerariesMap.put( busID, myItinerary );
                myCoordinator.recordAssignments(busID);

            } else {
                //Error log
            }

        }

        return myItinerariesMap;
    }

    public void receiveNewTrip(Trip newTrip){
        myTripCoordinator.addTrip(newTrip);
    }

    public void receiveNewTrips(Iterable<Trip> newTrips, int turn){

        myTripCoordinator.updateTime(turn);
        myTripCoordinator.handleNewTurn(newTrips);
    }

}
