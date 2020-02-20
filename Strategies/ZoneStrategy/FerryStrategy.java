package Strategies.ZoneStrategy;

import Strategies.Strategy;
import UniverseP.Units.*;

import java.util.*;

/**
 * Ferries all passengers from an assembly point to a distribution point
 * buses leave on a set schedule determined by the number of buses and distance between assembly/distribution point
 */
public class FerryStrategy implements Strategy {

    private Map<Integer, Bus> allBuses;
    private Queue<Bus> returningBuses;
    private Queue<Bus> standByBuses;
    private int timeElapsed;    //time since last bus left assembly point
    private int lastSent;
    private double maxTimeElapsed; //max time to evenly space out buses
    private Map<Integer, Trip> incomingTripIDs;
    private Set<Integer> awaitingDepartureTripIDs;
    private int turn;
    private ZoneItineraryFactory myItinFactory;

    public FerryStrategy(Map<Integer, Bus> allBuses, PartialZoneDefinition myDef) {
        this.allBuses = allBuses;
        this.returningBuses = setUpBusQ(allBuses);
        this.timeElapsed = 0;
        this.lastSent = Integer.MIN_VALUE;
        this.maxTimeElapsed =  calcMaxTime(myDef, allBuses.size());
        this.incomingTripIDs = new HashMap<>();
        this.awaitingDepartureTripIDs = new HashSet<>();
        this.turn = 0;
        this.myItinFactory = new ZoneItineraryFactory(myDef);
    }

    private Queue<Bus> setUpBusQ(Map<Integer, Bus> allBuses){
        Queue<Bus> returningBuses = new LinkedList<>();
        returningBuses.addAll(allBuses.values());
        return returningBuses;
    }

    private double calcMaxTime(PartialZoneDefinition myDef, int busCount){
        Location assemblyPoint = myDef.getAssemblyZone().getCentralStation();
        Location distributionPoint = myDef.getDistributeZone().getCentralStation();
        int distance = Location.getDistance(assemblyPoint, distributionPoint);
        return distance * 2.0 /  busCount;
    }

    public void receiveNewTrips(Iterable<Trip> newTrips, int turn){
        for(Trip myTrip: newTrips){
            this.receiveNewTrip(myTrip, turn);
        }
    }
    public void receiveNewTrip(Trip newTrip, int turn){
        this.incomingTripIDs.put(newTrip.getID(), newTrip);
        this.turn = turn;
    }

    public Set<Integer> getAssigned(){
        return allBuses.keySet();
    }
    public void recordAvailable(Set<Integer> additionalAvailable) {
        //not used in ferry strategy as all buses are in constant use
    }
    public void receivePickUpDropOffLog(List<ActionLog> actionsLogs) {

        awaitingDepartureTripIDs.clear();

        for(ActionLog myLog : actionsLogs){

            if(myLog.getMyType() != ActionType.DROPOFF){
                continue;
            }

            if(incomingTripIDs.keySet().contains(myLog.getTripID())){
                awaitingDepartureTripIDs.add(myLog.getTripID());
            }
        }
    }

    public List<Assignment> createAssignments() {
        if(timeElapsed < maxTimeElapsed){
            return new ArrayList<>();
        }

        List<Assignment> res = new ArrayList<>();

        Itinerary myItinerary = myItinFactory.createSecondLeg(awaitingDepartureTripIDs);
        res.add(new Assignment(myItinerary, returningBuses.peek().getBusID()));

        returningBuses.poll();

        awaitingDepartureTripIDs.clear();

        return res;
    }
}
