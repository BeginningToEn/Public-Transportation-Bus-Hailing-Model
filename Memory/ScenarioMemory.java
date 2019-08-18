package Memory;

import UniverseP.Units.Trip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EG OLIVER RC on 8/19/2017.
 */
public class ScenarioMemory {

    private Map<Integer, Trip> tripMap;

    public ScenarioMemory(){
        this.tripMap = new HashMap<>();
    }

    public void put(Trip myTrip){
        tripMap.put(myTrip.getID(), myTrip);
    }
}
