package Strategies;

import UniverseP.Units.Trip;

import java.util.Map;
import java.util.Set;

/**
 * Gets the a list of all buses and passengers and calculates the best bus itinerary
 */
public interface Strategy {
    void assignBuses();
    Map<Integer,Trip> createAssignments();
    void receiveNewTrip(Trip newTrip);
}
