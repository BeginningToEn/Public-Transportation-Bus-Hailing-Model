package Strategies;

import UniverseP.Units.Assignment;
import UniverseP.Units.Itinerary;
import UniverseP.Units.Trip;

import java.util.List;
import java.util.Map;

/**
 * Gets the a list of all buses and passengers and calculates the best bus itinerary
 */
public interface Strategy {

    List<Assignment> createAssignments();
    void receiveNewTrips(Iterable<Trip> newTrips, int turn);

    // refactored/remove after double check
    //void assignBuses();
    //Map<Integer,Itinerary> createItinerariesMap();
    //void receiveNewTrip(Trip newTrip);
}
