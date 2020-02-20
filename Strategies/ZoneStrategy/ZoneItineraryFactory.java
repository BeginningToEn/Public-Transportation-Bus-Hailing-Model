package Strategies.ZoneStrategy;

import UniverseP.Units.*;

import java.util.*;

/**
 * Splits a single trip into three itineraries.
 * First to deliver passenger to assembly point
 * Second to ferry passenger form assembly point to distribution point
 * Third to deliver passenger from distribution point to final destination
 */
public class ZoneItineraryFactory {

    private Map<Integer, Itinerary[]> splitTrips;
    private Location assemblyPoint;
    private Location distributionPoint;

    public ZoneItineraryFactory(PartialZoneDefinition myDef){
        this.splitTrips = new HashMap<>();
        this.assemblyPoint = myDef.getAssemblyZone().getCentralStation();
        this.distributionPoint = myDef.getDistributeZone().getCentralStation();
    }

    public void receiveNewTrip(Trip newTrip){
        Itinerary[] splitTrip = new Itinerary[3];
        splitTrip[0] = Itinerary.createIndirectItinerary(newTrip, newTrip.getSpawn(),
                new DropOffLocation(assemblyPoint, newTrip.getID()));
        splitTrip[1] = Itinerary.createIndirectItinerary(newTrip, new PickUpLocation(assemblyPoint, newTrip.getID()),
                new DropOffLocation(distributionPoint, newTrip.getID()));
        splitTrip[2] = Itinerary.createIndirectItinerary(newTrip, new PickUpLocation(distributionPoint, newTrip.getID()),
                newTrip.getDestination());

        splitTrips.put(newTrip.getID(), splitTrip);
    }

    public Itinerary createSecondLeg(Collection<Integer> tripIDs){
        return Itinerary.createFerryItinerary(tripIDs, new PickUpLocation(assemblyPoint, tripIDs),
                new DropOffLocation(distributionPoint, tripIDs));
    }

    public List<Itinerary> getFirstLeg(Iterable<Integer> tripIDs){
        //first leg corresponds to index 0
        return getIthLeg(tripIDs, 0);
    }

    public List<Itinerary> getSecondLeg(Iterable<Integer> tripIDs){
        //first leg corresponds to index 0
        return getIthLeg(tripIDs, 1);
    }

    public List<Itinerary> getThirdLeg(Iterable<Integer> tripIDs){
        //first leg corresponds to index 0
        return getIthLeg(tripIDs, 2);
    }

    private List<Itinerary> getIthLeg(Iterable<Integer> tripIDs, int i){

        List<Itinerary> output = new ArrayList<>();

        for( int tripID : tripIDs){
            output.add( splitTrips.get(tripID)[i]);
        }

        return output;
    }
}
