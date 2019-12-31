package Memory;

import UniverseP.ScenarioSimulation.ScenarioDefinition;
import UniverseP.ScenarioSimulation.TripSource;
import UniverseP.TripFactory.TripTimeTable;
import UniverseP.Units.Trip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EG OLIVER RC on 8/19/2017.
 */
public class ScenarioMemory {

    private HashMap<Integer, TripMemory> tripMemoryByID;
    private HashMap<Integer, BusMemory> busMemoryByID;

    private ScenarioDefinition myDef;
    private TripSource mySource;

    public ScenarioMemory(ScenarioDefinition myDef){
        this.myDef = myDef;
        this.tripMemoryByID = new HashMap<>();
        this.busMemoryByID = new HashMap<>();
    }

    public void logCreation(Trip myTrip){
        tripMemoryByID.put(myTrip.getID(), new TripMemory(myTrip));
    }

    public void logCreation(Iterable<Trip> myTrips) {
        for(Trip it : myTrips){
            this.logCreation(it);
        }
    }

    public void logAssignment(int tripID, int busID, int turn){
        tripMemoryByID.get(tripID).setAssigned(busID, turn);
    }


    public void logOnboarding(int tripID, int turn){

        tripMemoryByID.get(tripID).setTimePickedUp(turn);
    }

    public void logOffboarding(int tripID, int turn){

        tripMemoryByID.get(tripID).setTimeDroppedOff(turn);
    }

    public void print(){
        this.tripMemoryByID.forEach((k, v) -> System.out.println((v)));
    }

    public HashMap<Integer, TripMemory> getTrips(){
        return tripMemoryByID;
    }

}
