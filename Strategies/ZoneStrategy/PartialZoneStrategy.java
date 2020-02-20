package Strategies.ZoneStrategy;

import Strategies.SinglePassengerStrategy.SP_TripCoordinator;
import Strategies.SinglePassengerStrategy.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.ScenarioSimulation.BusCoordinator;
import UniverseP.Units.ActionLog;
import UniverseP.Units.Assignment;
import UniverseP.Units.Bus;
import UniverseP.Units.Trip;

import java.util.*;

/**
 * Created by EG OLIVER RC on 2/11/2020.
 */
public class PartialZoneStrategy implements Strategy {

    private BusSplit myBusSplit;

    private SinglePassengerStrategy outsideZoneStrat;
    private ZoneStrategy zoneStrat;

    private PartialZoneDefinition myZoneDef;

    private BusCoordinator myCoordinators;
    private SP_TripCoordinator myTripCoordinator;

    public PartialZoneStrategy(Map<Integer, Bus> allBuses, PartialZoneDefinition myZoneDef){

        this.myBusSplit = new BusSplit(allBuses, myZoneDef);
        this.outsideZoneStrat = new SinglePassengerStrategy(myBusSplit.getOutsideZoneBuses());
        this.zoneStrat = new ZoneStrategy(myBusSplit.getAssemblyBuses(), myBusSplit.getFerryBuses(),
                myBusSplit.getDistributeBuses(), myZoneDef);

        this.myZoneDef = myZoneDef;
        this.myCoordinators = BusCoordinator.createBusCoordinator(allBuses.keySet());
    }

    public void receiveNewTrips(Iterable<Trip> newTrips, int turn){
        for(Trip myTrip : newTrips){
            receiveNewTrip(myTrip, turn);
        }
    }

    public void receiveNewTrip(Trip newTrip, int turn){
        if(isValid(newTrip)){
            zoneStrat.receiveNewTrip(newTrip, turn);
        } else {
            outsideZoneStrat.receiveNewTrip(newTrip, turn);
        }
    }
    public List<Assignment> createAssignments(){
        List<Assignment> res = new ArrayList<>();

        return res;
    }
    public Set<Integer> getAssigned(){
        //pass
        return new HashSet<Integer>();
    }
    public void recordAvailable(Set<Integer> additionalAvailable){

    }
    public void receivePickUpDropOffLog(List<ActionLog> actionsLogs){
        zoneStrat.receivePickUpDropOffLog(actionsLogs);
    }

    public boolean isValid(Trip myTrip){
        return myZoneDef.getAssemblyZone().isInZone(myTrip.getSpawn())
                && myZoneDef.getDistributeZone().isInZone(myTrip.getSpawn());
    }
}
