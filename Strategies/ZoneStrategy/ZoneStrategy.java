package Strategies.ZoneStrategy;

import Strategies.SinglePassengerStrategy.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.Units.ActionLog;
import UniverseP.Units.Assignment;
import UniverseP.Units.Bus;
import UniverseP.Units.Trip;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by EG OLIVER RC on 2/17/2020.
 */
public class ZoneStrategy implements Strategy {

    private SinglePassengerStrategy assemblyStrat;
    private SinglePassengerStrategy ferryStrat;
    private SinglePassengerStrategy distributeStrat;

    private DropOffDataParser myParser;

    public ZoneStrategy(Map<Integer, Bus> assemblyBuses, Map<Integer, Bus> ferryBuses,
                        Map<Integer, Bus> distributeBuses, PartialZoneDefinition myZoneDef) {
        this.assemblyStrat = new SinglePassengerStrategy(assemblyBuses);
        this.ferryStrat = new SinglePassengerStrategy(ferryBuses);
        this.distributeStrat = new SinglePassengerStrategy(distributeBuses);

    }


    void receiveNewTrips(Iterable<Trip> newTrips, int turn);
    void receiveNewTrip(Trip newTrip, int turn);
    Set<Integer> getAssigned();
    void recordAvailable(Set<Integer> additionalAvailable);
    void receivePickUpDropOffLog(List<ActionLog> actionsLogs);
    List<Assignment> createAssignments();
}
