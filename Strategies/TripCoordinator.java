package Strategies;

import UniverseP.Units.Trip;
import java.util.Set;

/**
 * Created by EG OLIVER RC on 11/5/2019.
 */
public interface TripCoordinator {
    void receiveNewTrips(Set<Trip> newTrips);
}
