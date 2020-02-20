package Strategies;

import UniverseP.Units.ActionLog;
import UniverseP.Units.Assignment;
import UniverseP.Units.Itinerary;
import UniverseP.Units.Trip;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Gets the a list of all buses and passengers and calculates the best bus itinerary
 */
public interface Strategy {

    List<Assignment> createAssignments();
    void receiveNewTrips(Iterable<Trip> newTrips, int turn);
    void receiveNewTrip(Trip newTrip, int turn);
    Set<Integer> getAssigned();
    void recordAvailable(Set<Integer> additionalAvailable);
    void receivePickUpDropOffLog(List<ActionLog> actionsLogs);

    // refactored/remove after double check
    //void assignBuses();
    //Map<Integer,Itinerary> createItinerariesMap();
    //void receiveNewTrip(Trip newTrip);
}
